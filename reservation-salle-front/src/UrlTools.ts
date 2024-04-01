import {User} from "./model/user.model";


export const getUriSalle = (url: string) => {
    const baseUri = "http://localhost"
    const port= "8080"
    return `${baseUri}:${port}/api/rsv/${url}`
}

export const getUriUser = (url: string, user?: User) => {
    const baseUri = "http://localhost"
    const port= "8080"
    return `${baseUri}:${port}/api/user/${url}`
}

export const getUriRsv = (url: string) => {
    const baseUri = "http://localhost"
    const port= "8080"
    return `${baseUri}:${port}/api/rsv/${url}`
}

export const getUriCreneau = (url: string) => {
    const baseUri = "http://localhost"
    const port= "8080"
    return `${baseUri}:${port}/api/creneau/${url}`
}


