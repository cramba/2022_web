import { reactive, readonly } from "vue";

import { Client } from '@stomp/stompjs';

const wsurl = `ws://${window.location.host}/stompbroker`

import { useLogin } from '@/services/useLogin'
const { logindata } = useLogin()

export function useGebot(angebotid: number) {
    /*
     * Mal ein Beispiel für CompositionFunction mit Closure/lokalem State,
     * um parallel mehrere *verschiedene* Versteigerungen managen zu können
     * (Gebot-State ist also *nicht* Frontend-Global wie Angebot(e)-State)
     */

    // STOMP-Destination
    const DEST = `/topic/gebot/${angebotid}`
    


    ////////////////////////////////

    // entspricht GetGebotResponseDTO.java aus dem Spring-Backend
    interface IGetGebotResponseDTO {
        gebotid: number,
        gebieterid: number,
        gebietername: string,
        angebotid: number,
        angebotbeschreibung: string,
        betrag: number,
        gebotzeitpunkt: string // kommt als ISO-DateTime String-serialisiert an!
    }

    // Basistyp für gebotState
    interface IGebotState {
        angebotid: number,              // ID des zugehörigen Angebots
        topgebot: number,               // bisher höchster gebotener Betrag
        topbieter: string,              // Name des Bieters, der das aktuelle topgebot gemacht hat
        gebotliste: IGetGebotResponseDTO[], // Liste der Gebote, wie vom Backend geliefert
        receivingMessages: boolean,     // Status, ob STOMP-Messageempfang aktiv ist oder nicht
        errormessage: string            // (aktuelle) Fehlernachricht oder Leerstring
    }



    /* reaktives Objekt auf Basis des Interface <IGebotState> */
    const gebotState = reactive<IGebotState>({angebotid: 0, topgebot: 0, topbieter: "", gebotliste: [], receivingMessages: false, errormessage: "" })
    


    function processGebotDTO(gebotDTO: IGetGebotResponseDTO) {
        const dtos = JSON.stringify(gebotDTO)
        console.log(`processGebot(${dtos})`)

        const gebot = gebotState.gebotliste.find((e) => e.gebieterid === gebotDTO.gebieterid)
        
        if (gebot == undefined) {
            gebotState.gebotliste.push(gebotDTO)
        }else {
            gebot.betrag = gebotDTO.betrag
            gebot.gebotzeitpunkt = gebotDTO.gebotzeitpunkt
        }

       
        /*
         * suche Angebot für 'gebieter' des übergebenen Gebots aus der gebotliste (in gebotState)
         * falls vorhanden, hat der User hier schon geboten und das Gebot wird nur aktualisiert (Betrag/Gebot-Zeitpunkt)
         * falls nicht, ist es ein neuer Bieter für dieses Angebot und das DTO wird vorne in die gebotliste des State-Objekts aufgenommen
         */

        if (gebotDTO.betrag > gebotState.topgebot){
            gebotState.topbieter = gebotDTO.gebietername
            gebotState.topgebot = gebotDTO.betrag
        }

        /*
         * Falls gebotener Betrag im DTO größer als bisheriges topgebot im State,
         * werden topgebot und topbieter (der Name, also 'gebietername' aus dem DTO)
         * aus dem DTO aktualisiert
         */

    }


    function receiveGebotMessages() {

        const stompclient = new Client({brokerURL: wsurl})

        stompclient.onWebSocketError = (event) => { 
            /* WS-Fehler */
            gebotState.receivingMessages = false
            gebotState.errormessage = "WS-Fehler"
        }
        stompclient.onStompError = (frame) => { 
            /* STOMP-Fehler */ 
            gebotState.receivingMessages = false
            gebotState.errormessage = "STOMP Fehler"
        }

        stompclient.onConnect = (frame) => {
            stompclient.subscribe(DEST, (message) => {
                const empfangen:IGetGebotResponseDTO = JSON.parse(message.body)
                processGebotDTO(empfangen)
                gebotState.receivingMessages = true
            })
        }
        stompclient.activate();
        


        /*
         * analog zu Message-Empfang bei Angeboten
         * wir verbinden uns zur brokerURL (s.o.),
         * bestellen Nachrichten von Topic DEST (s.o.)
         * und rufen die Funktion processGebotDTO() von oben
         * für jede neu eingehende Nachricht auf diesem Topic auf.
         * Eingehende Nachrichten haben das Format IGetGebotResponseDTO (s.o.)
         * Die Funktion aktiviert den Messaging-Client nach fertiger Einrichtung.
         * 
         * Bei erfolgreichem Verbindungsaubau soll im State 'receivingMessages' auf true gesetzt werden,
         * bei einem Kommunikationsfehler auf false 
         * und die zugehörige Fehlermeldung wird in 'errormessage' des Stateobjekts geschrieben
         */



    }



    async function updateGebote() {

        console.log("UPDATE GEBOTE")
        fetch('/api/gebot')
        .then(resp => {
            if (!resp.ok) {
                gebotState.gebotliste = [];
                gebotState.errormessage = resp.statusText
                console.log("fehler beim Response")
                throw new Error(resp.statusText);
            }
            return resp.json();
        })
        .then((jsondata: IGetGebotResponseDTO[]) => {
            gebotState.gebotliste = jsondata.filter(d => d.angebotid == angebotid)
            receiveGebotMessages()

            let max = 0
            gebotState.gebotliste.forEach(gebot => {
                if (gebot.betrag > max){
                    gebotState.topgebot = gebot.betrag
                    gebotState.topbieter = gebot.gebietername
                }
            })

            gebotState.errormessage= ""


        })
        .catch(reason => {
            gebotState.gebotliste = []
            gebotState.errormessage = reason
        })
        /*
         * holt per fetch() auf Endpunkt /api/gebot die Liste aller Gebote ab
         * (Array vom Interface-Typ IGetGebotResponseDTO, s.o.)
         * und filtert diejenigen für das Angebot mit Angebot-ID 'angebotid' 
         * (Parameter der useGebot()-Funktion, s.o.) heraus. 
         * Falls erfolgreich, wird 
         *   - das Messaging angestoßen (receiveGebotMessages(), s.o.), 
         *     sofern es noch nicht läuft
         *   - das bisherige maximale Gebot aus der empfangenen Liste gesucht, um
         *     die State-Properties 'topgebot' und 'topbieter' zu initialisieren
         *   - 'errormessage' auf den Leerstring gesetzt
         * Bei Fehler wird im State-Objekt die 'gebotliste' auf das leere Array 
         * und 'errormessage' auf die Fehlermeldung geschrieben.
         */

        


    }


    // Analog Java-DTO AddGebotRequestDTO.java
    interface IAddGebotRequestDTO {
        benutzerprofilid: number,
        angebotid: number,
        betrag: number
    }

    

    async function sendeGebot(betrag: number) {
        const angebot = gebotState.gebotliste.find(x => x.angebotid === angebotid)
        const neuesGebot = <IAddGebotRequestDTO>({benutzerprofilid: angebot!.gebieterid, angebotid: angebotid, betrag: betrag})
        const response = await fetch('/api/gebot', {
            method: 'POST',
            body: JSON.stringify(neuesGebot)
        })
        .then(resp => {
            if (!resp.ok) {
                gebotState.errormessage=""
                return resp.json()
            }
        })
        .catch(reason => {  
            gebotState.errormessage=reason
        })

        const idvalue = response.text()
        console.log(idvalue)
        
        /*
         * sendet per fetch() POST auf Endpunkt /api/gebot ein eigenes Gebot,
         * schickt Body-Struktur gemäß Interface IAddGebotRequestDTO als JSON,
         * erwartet ID-Wert zurück (response.text()) und loggt diesen auf die Console
         * Falls ok, wird 'errormessage' im State auf leer gesetzt,
         * bei Fehler auf die Fehlermeldung
         */

    }
    

    // Composition Function -> gibt nur die nach außen freigegebenen Features des Moduls raus
    return {
        gebote: readonly(gebotState),
        updateGebote,
        sendeGebot
    }
}



