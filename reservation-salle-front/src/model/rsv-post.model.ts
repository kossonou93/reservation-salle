import {User} from "./user.model";
import {Creneau} from "./creneau.model";

export interface RsvPost{
    id?: number;
    jour?: string;
    user?: number;
    creneau?: number;
}