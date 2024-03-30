


export const getUriSalle = (url: string) => {
    const baseUri = "http://192.168.1.19"
    const port= "8080"
    return `${baseUri}:${port}/api/rsv/${url}`
}


