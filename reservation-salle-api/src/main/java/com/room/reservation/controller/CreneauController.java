package com.room.reservation.controller;

import com.room.reservation.model.Creneau;
import com.room.reservation.service.IBaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = { "*" }, exposedHeaders = { "Content-Disposition" })
@RequestMapping(path = "/api/creneau")
public class CreneauController extends BaseController<Creneau> {

    public CreneauController(IBaseService<Creneau> baseService){
        super(baseService);
    }
}
