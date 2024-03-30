import {Button, Card, Select} from "@mui/material";
import {CardBody} from "react-bootstrap";
import dayGridPlugin from '@fullcalendar/daygrid'
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import  { SelectChangeEvent } from '@mui/material/Select';
import FullCalendar from "@fullcalendar/react";
import {useEffect, useState} from "react"; // a plugin!
import interactionPlugin from "@fullcalendar/interaction"
import axios from "axios";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {getUriSalle} from "../../UrlTools"; // needed for dayClick
export const ReservedRoom =  () => {
    const [ salle , setSalle ] = useState("");
    const [ salles , setSalles ] = useState([]);
    const [showCrenaux , setShowCreneau] = useState(false)

    useEffect(() => {
        getSalles()
    }, [])

    const handleClose = () => {
        setShowCreneau(false);
    };

    const handleChange = (e: any) => {
        setSalle(e.target.value)
    }

    const handleDateClick = (arg: any) => {
        alert(arg.dateStr)
        setShowCreneau(true)
    }


    const getSalles = () => {
       axios.get(getUriSalle("salles")).then((response) => {
           console.log(response)
           if (response.hasOwnProperty("data")) {
               setSalles(response.data.object)
           }
       }).catch(error => {
           setSalles([])
       })
    }

    return (
        <Card
            style={{display: "flex" , flexDirection: "row" , justifyItems: "center" , padding: "20px"}}
        >
            <CardBody
                style={{width: "30%" , padding: "20px"}}
            >
                <div style={{marginTop: "50px"}}>
                    <FormControl fullWidth >
                        <InputLabel id="demo-simple-select-label">Salles</InputLabel>
                        <Select
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            value={salle}
                            label="Choose Room"
                            onChange={handleChange}
                        >
                            {salles.length == 0 &&  <MenuItem > Pas de salle disponible</MenuItem >}
                            {salles && salles.map((s: any , idx) =>  <MenuItem  key={idx} value={s.id}> {s.name != undefined ? s.name : ""}</MenuItem >)}
                        </Select>
                    </FormControl>
                </div>
            </CardBody>
            <CardBody
                style={{width: "60%"}}
            >
                <FullCalendar
                    plugins={[ dayGridPlugin, interactionPlugin ]}
                    initialView="dayGridMonth"
                    dateClick={handleDateClick}
                />

            </CardBody>
            <Dialog
                open={showCrenaux}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    {"Use Google's location service?"}
                </DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                        Let Google help apps determine location. This means sending anonymous
                        location data to Google, even when no apps are running.
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Fermer</Button>
                    <Button onClick={handleClose} autoFocus>
                        Valider
                    </Button>
                </DialogActions>
            </Dialog>
        </Card>
    )
}
