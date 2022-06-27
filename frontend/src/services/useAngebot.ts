import { reactive, readonly } from "vue";
import type { AngebotListeDing, IAngebotListeItem } from "./IAngebotListeItem";
import {Client, type Message} from '@stomp/stompjs';
import type { IBackendInfoMessage } from "./IBackendInfoMessage";

export interface IAngebotState {
    angebotliste: IAngebotListeItem[],
    errormessage: string
}

const angebotState = reactive<IAngebotState>({angebotliste: [], errormessage:""})



export function useAngebot() {
    return {
        angebote: readonly(angebotState)
    }
}

export function updateAngebote() { 
    console.log("UPDATE ANGEBOTE")
    fetch('/api/angebot')
    .then(resp => {
        if (!resp.ok) {
            throw new Error(resp.statusText);
        }
        return resp.json();
    })
    .then((jsondata: AngebotListeDing[]) => {
        angebotState.angebotliste = jsondata
        angebotState.errormessage = ""
    })
    .catch(reason => {
        angebotState.errormessage = 'FEHLER: ${reason}';
    });
}

export function receiveAngebotMessages() {
    //const wsurl = 'ws://${window.location.host}/stompbroker';
    const wsurl = 'ws://localhost:3000/stompbroker'
    const DEST = '/topic/angebot'; 
    const stompclient = new Client({brokerURL: wsurl})

    stompclient.onWebSocketError = (event) => { /* WS-Fehler */ } 
    stompclient.onStompError = (frame) => { /* STOMP-Fehler */ }

    stompclient.onConnect = (frame) => {
        stompclient.subscribe(DEST, (message) => {
            const empfangen:IBackendInfoMessage = JSON.parse(message.body) 
            updateAngebote()
        })
    }
    stompclient.activate();
}