package com.room.reservation.service;

import com.room.reservation.model.Creneau;
import com.room.reservation.model.User;
import com.room.reservation.repository.CreneauRepository;
import com.room.reservation.service.face.ICreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreneauService implements ICreneauService {

    @Autowired
    private CreneauRepository creneauRepository;


    @Override
    public Creneau enregistrer(Creneau creneau) {
        Creneau res = null;
        try {
            res = creneauRepository.save(creneau);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Creneau modifier(Creneau creneau) {
        return null;
    }

    @Override
    public void supprimer(Long id) {
        try {
            creneauRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
