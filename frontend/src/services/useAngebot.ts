import { reactive, readonly } from "vue";
import type { AngebotListeDing, IAngebotListeItem } from "./IAngebotListeItem";

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