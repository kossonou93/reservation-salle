import {Salle} from "./salle.model";

export interface Creneau{
    id?: number;
    hdebut: number;
    hfin: number;
    mdebut: number;
    mfin: number;
    salle: Salle;
}