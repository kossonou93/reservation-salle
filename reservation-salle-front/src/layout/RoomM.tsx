import {Card} from "@mui/material";
import { Outlet } from "react-router-dom";
import Header from "../components/Header";

export default () => {
    return (
        <>
            <Header/>
            <div style={{height: "80vh" , padding: "20px"}}>
                <Outlet />
            </div>
        </>

    )
}
