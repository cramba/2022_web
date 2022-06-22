import { reactive, readonly } from "vue";
import type { IAngebotListeItem } from "./IAngebotListeItem";

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