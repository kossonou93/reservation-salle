import {Creneau} from "./creneau.model";
import {Rsv} from "./rsv.model";
import {User} from "./user.model";

export interface CreneauSalle{
    id?: number;
    creneau: Creneau;
    rsv: Rsv;
}