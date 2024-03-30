package com.room.reservation.service;

import com.room.reservation.model.Salle;
import com.room.reservation.model.User;
import com.room.reservation.repository.SalleRepository;
import com.room.reservation.service.face.ISalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalleService implements ISalleService {

    @Autowired
    private SalleRepository salleRepository;

    @Override
    public Salle enregistrer(Salle salle) {
        Salle res = null;
        try {
            res = salleRepository.save(salle);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Salle modifier(Salle salle) {
        return null;
    }

    @Override
    public void supprimer(Long id) {
        try {
            salleRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
