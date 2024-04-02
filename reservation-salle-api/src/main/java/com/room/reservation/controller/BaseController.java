package com.room.reservation.controller;

import com.room.reservation.model.User;
import com.room.reservation.service.IBaseService;
import com.room.reservation.web.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class BaseController <T>{

    @Autowired
    private IBaseService<T> baseservice;

    public BaseController(IBaseService<T> baseService) {
        super();
        this.baseservice = baseService;
    }

    @PostMapping("/enregistrer")
    public Response enregistrer(@RequestBody T entity) {
        T res;
        try {
            res = baseservice.enregistrer(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(2, null);
        }
        return new Response(0, res);
    }

    @DeleteMapping("/supprimer/{id}")
    public Response supprimer(@PathVariable Long id) {
        try {
            baseservice.supprimer(id);
        } catch (Exception e) {
            return new Response(2, e.getMessage());
        }
        return new Response(0, null);
    }
}
