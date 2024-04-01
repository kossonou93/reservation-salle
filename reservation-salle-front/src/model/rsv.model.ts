import {User} from "./user.model";
import {Creneau} from "./creneau.model";

export interface Rsv{
    id?: number;
    jour?: string;
    user?: User;
    creneau?: Creneau;
}