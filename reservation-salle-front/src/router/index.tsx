

import Dashboad from "../page/dashboad/Dashboad";
import ListeRooms from "../page/liste/ListeRooms";
import {AgendaRooms} from "../page/agenda/AgendaRooms";
import {ReservedRoom} from "../page/reserved/ReservedRoom";

export const RouterApp = [
    {path: "/", element: <Dashboad />, name: "Dashboad" },
    {path: "/rooms" , element: <ListeRooms /> , name: "rooms" },
    {path: "/agenda-room" , element: <AgendaRooms />  , name: "agenda-rooms"},
    {path: "/reserved-room" , element: <ReservedRoom /> , name: "reserved-room" },
]
