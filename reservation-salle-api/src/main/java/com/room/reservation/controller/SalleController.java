package com.room.reservation.controller;

import com.room.reservation.model.Salle;
import com.room.reservation.service.IBaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = { "*" }, exposedHeaders = { "Content-Disposition" })
@RequestMapping(path = "/api/salle")
public class SalleController extends BaseController<Salle>{

    public SalleController(IBaseService<Salle> baseService){
        super(baseService);
    }
}
