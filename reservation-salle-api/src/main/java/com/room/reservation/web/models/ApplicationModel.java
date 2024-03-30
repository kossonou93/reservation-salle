package com.room.reservation.web.models;

import com.room.reservation.domain.AgendaSalleJour;
import com.room.reservation.model.Creneau;
import com.room.reservation.model.Rsv;
import com.room.reservation.model.Salle;
import com.room.reservation.model.User;
import com.room.reservation.service.IMetier;
import com.room.reservation.web.helpers.Static;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ApplicationModel implements IMetier {

    // la couche [metier]
    @Autowired
    private IMetier metier;
    private List<Salle> salles;
    private List<User> users;
    private List<String> messages;

    @PostConstruct
    public void init(){
        // on récupère les salles et les users
        try {
            salles = metier.getAllSalles();
            users = metier.getAllUsers();
        } catch (Exception e){
            messages = Static.getErreursForException(e);
        }
    }

    //getter
    public List<String> getMessages(){
        return messages;
    }

    @Override
    public List<User> getAllUsers() {
        return metier.getAllUsers();
    }

    @Override
    public List<Salle> getAllSalles() {
        return metier.getAllSalles();
    }

    @Override
    public List<Creneau> getAllCreneaux(Long idSalle) {
        return metier.getAllCreneaux(idSalle);
    }

    @Override
    public List<Rsv> getRsvSalleJour(Long idSalle, Date jour) {
        return metier.getRsvSalleJour(idSalle, jour);
    }

    @Override
    public User getUserById(Long id) {
        return metier.getUserById(id);
    }

    @Override
    public Salle getSalleById(Long id) {
        return metier.getSalleById(id);
    }

    @Override
    public Rsv getRsvByid(Long id) {
        return metier.getRsvByid(id);
    }

    @Override
    public Creneau getCreneauById(Long id) {
        return metier.getCreneauById(id);
    }

    @Override
    public Rsv ajouterRsv(Date jour, Creneau creneau, User user) {
        return metier.ajouterRsv(jour, creneau, user);
    }

    @Override
    public void supprimerRsv(Long idRsv) {
        metier.supprimerRsv(idRsv);
    }

    @Override
    public AgendaSalleJour getAgendaSalleJour(Long idSalle, Date jour) {
        return metier.getAgendaSalleJour(idSalle, jour);
    }
}
