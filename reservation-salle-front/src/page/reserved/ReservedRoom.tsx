import {Button, Card, Select} from "@mui/material";
import {CardBody} from "react-bootstrap";
import dayGridPlugin from '@fullcalendar/daygrid'
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import  { SelectChangeEvent } from '@mui/material/Select';
import FullCalendar from "@fullcalendar/react";
import React, {useEffect, useState} from "react"; // a plugin!
import interactionPlugin from "@fullcalendar/interaction"
import axios from "axios";
import CloseIcon from '@mui/icons-material/Close'
import {getUriSalle, getUriUser} from "../../UrlTools"; // needed for dayClick
import Modal from '@mui/material/Modal';
import Typography from '@mui/material/Typography';
import {Salle} from "../../model/salle.model";
import {Creneau} from "../../model/creneau.model";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer";
import {CreneauSalle} from "../../model/creneau-salle.model";
import IconButton from "@mui/material/IconButton";
import {useNavigate} from "react-router-dom";
import {RsvPost} from "../../model/rsv-post.model";
import {toaster} from "evergreen-ui";

export const ReservedRoom =  () => {
    const navigate = useNavigate()
    const [ salle , setSalle ] = useState("");
    const [ salles , setSalles ] = useState<Salle[]>([]);
    const [showCrenaux , setShowCreneau] = useState(false);
    const [open, setOpen] = React.useState(false);
    const [creneau, setCreneau] = useState<Creneau>();
    const [crenaux, setCreneaux] = useState<Creneau[]>([]);
    const [crenauSalles, setCreneauSalles] = useState<CreneauSalle[]>([]);
    const [rsv, setRsv] = useState<RsvPost>({
        jour: "",
        idUser: 0,
        idCreneau: 0
    });
    const handleOpen = () => setOpen(true);
    /*const handleClose = () => {
        setShowCreneau(false);
    };*/

    const handleClose = () => setOpen(false);

    const style = {
        position: 'absolute' as 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 600,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };

    useEffect(() => {
        getSalles();
        const userString = localStorage.getItem('user');
        if (userString) {
            const user = JSON.parse(userString);
            const id = user.id;
            setRsv(prevState => ({
                ...prevState,
                idUser: id,
            }));
        } else {
        }
    }, [])

    const handleChange = (e: any) => {
        setSalle(e.target.value);
    }

    const handleDateClick = (arg: any) => {
        if (!salle){
            toaster.danger("NOTIFICATION", {
                description: "Veillez sélectionner la salle svp!"
            });
        }else{
            handleOpen();
            getCreneaux(parseInt(salle), arg.dateStr);
            setRsv(prevState => ({
                ...prevState,
                jour: arg.dateStr,
            }));
        }
        //setShowCreneau(true)
    }

    const handleReservation = (id: number) => {
        setRsv(prevState => ({
            ...prevState,
            idCreneau: id,
        }));
        if (localStorage.getItem('token') === "" && localStorage.getItem('token') === null){
            toaster.danger("NOTIFICATION", {
                description: "Veillez vous connecter!"
            });
            navigate('/login');
        }else{
           axios.post(getUriSalle(`ajouter-rsv`), {"jour": rsv.jour, "idUser": rsv.idUser, "idCreneau": id}).then((response) => {
               if (response.hasOwnProperty("data")) {
                   handleClose();
                   toaster.success("NOTIFICATION", {
                       description: "La salle à été réservée avec succès!"
                   });
               }
           }).catch(error => {
           })
        }
    }

    const getSalles = () => {
       axios.get(getUriSalle("salles")).then((response) => {
           if (response.hasOwnProperty("data")) {
               setSalles(response.data.object)
           }
       }).catch(error => {
           setSalles([])
       })
    }

    const getCreneaux = (idSalle: number, jour: string) => {
        axios.get(getUriSalle(`agenda-salle/${idSalle}/${jour}`)).then((response)=> {
            if (response.hasOwnProperty("data")){
                setCreneauSalles(response.data.object.creneauxSalle);
            }
        }).catch(error => {
            setCreneaux([]);
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
            <div>
                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                >
                    <Box sx={style}>
                        <Typography id="modal-modal-title" variant="h6" component="h2">
                            Liste des Créneaux
                        </Typography>
                        <IconButton
                            aria-label="Fermer"
                            onClick={handleClose}
                            sx={{ position: 'absolute', top: 0, right: 0 }}
                        >
                            <CloseIcon />
                        </IconButton>
                        <div>
                            <TableContainer component={Paper}>
                                <Table sx={{ minWidth: 100 }} aria-label="simple table">
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>#</TableCell>
                                            <TableCell align="right">Heure Début</TableCell>
                                            <TableCell align="right">Heure Fin</TableCell>
                                            <TableCell align="right">Réserver</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {crenauSalles.filter(cs => cs.rsv === null).map((cs, index) => (
                                            <TableRow key={index}>
                                                <TableCell component="th" scope="row">{index + 1}</TableCell>
                                                <TableCell align="right">{cs.creneau.hdebut}h:{cs.creneau.mdebut}</TableCell>
                                                <TableCell align="right">{cs.creneau.hfin}h:{cs.creneau.mfin}</TableCell>
                                                <TableCell align="right">
                                                    <button className="lButton" onClick={() => handleReservation(cs.creneau.id!)}>
                                                        Réserver
                                                    </button>
                                                </TableCell>
                                            </TableRow>
                                        ))}
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </div>
                    </Box>
                </Modal>
            </div>
        </Card>
    )
}
