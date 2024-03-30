import {useEffect, useState} from "react";
import {Box, Card, CardHeader} from "@mui/material";
import {CardBody} from "react-bootstrap";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';

 const ListeRooms =  () => {


    const handleChange = (event: SelectChangeEvent) => {
      //  setStatus(event.target.value);
    };



    const  loadListRooms = () => {
        console.log("Load data")
    }

    return (
        <Card>
            <CardHeader>
                <Box>
                    <FormControl sx={{ m: 1, minWidth: 120 }} size="small">
                        <InputLabel id="demo-select-small-label">Age</InputLabel>
                        <Select
                            labelId="demo-select-small-label"
                            id="demo-select-small"
                            value={"All"}
                            label="Age"
                            onChange={handleChange}
                        >
                            <MenuItem value="all">
                                <em>All</em>
                            </MenuItem>
                            <MenuItem value={"busy"}>Busy</MenuItem>
                            <MenuItem value={"available"}>Available</MenuItem>
                        </Select>
                    </FormControl>
                </Box>
            </CardHeader>
            <CardBody>
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 650 }} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>#</TableCell>
                                <TableCell align="right">Room</TableCell>
                                <TableCell align="right">state</TableCell>
                                <TableCell align="right">count</TableCell>
                                <TableCell align="right">Actions</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>

                        </TableBody>
                    </Table>
                </TableContainer>
            </CardBody>
        </Card>
    )
}

export default ListeRooms
