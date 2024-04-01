import {Role} from "./role.model";

export interface User{
    id?: number;
    version?: number;
    email: string;
    username: string;
    password: string;
    contact?: string;
    roles: Role[];
}