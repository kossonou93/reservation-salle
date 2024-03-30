import { Container } from "@mui/material";
import { Suspense } from "react";
import {Routes, Route } from "react-router-dom";
import Footer from "./components/Footer";
import RoomM from "./layout/RoomM";
import {RouterApp} from "./router";

import Loader from "./components/Loader";
import Dashboad from "./page/dashboad/Dashboad";

const App = () => {


    return (
        <Container
        >
            <Routes>
                <Route element={<RoomM />}>
                    <Route index element={<Dashboad />} />
                    {RouterApp.map((route: any, index: any) => {
                        return (
                            <Route
                                key={index}
                                path={route.path}
                                element={
                                    <Suspense fallback={<Loader />}>
                                        {" "}
                                        {route.element}
                                    </Suspense>
                                }
                            />
                        );
                    })}
                </Route>
            </Routes>
           <Footer/>
        </Container>
    );
}

export default App
