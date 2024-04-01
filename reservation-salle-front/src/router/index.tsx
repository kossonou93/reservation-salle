

import Dashboad from "../page/dashboad/Dashboad";
import ListeRooms from "../page/liste/ListeRooms";
import {AgendaRooms} from "../page/agenda/AgendaRooms";
import {ReservedRoom} from "../page/reserved/ReservedRoom";
import Login from "../page/login/Login";
import Register from "../page/register/Register";

export const RouterApp = [
    {path: "/", element: <Dashboad />, name: "Dashboad" },
    {path: "/login", element: <Login />, name: "Login"},
    {path: "/register", element: <Register /> , name: "Register"},
    {path: "/rooms" , element: <ListeRooms /> , name: "rooms" },
    {path: "/agenda-room" , element: <AgendaRooms />  , name: "agenda-rooms"},
    {path: "/reserved-room" , element: <ReservedRoom /> , name: "reserved-room" },
]
