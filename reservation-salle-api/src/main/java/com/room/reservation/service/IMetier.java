package com.room.reservation.service;

import com.room.reservation.domain.AgendaSalleJour;
import com.room.reservation.model.Creneau;
import com.room.reservation.model.Rsv;
import com.room.reservation.model.Salle;
import com.room.reservation.model.User;

import java.util.Date;
import java.util.List;

public interface IMetier {
    
    // liste des users
    public List<User> getAllUsers();
    
    //liste des Salles
    public List<Salle> getAllSalles();
    
    //liste des créneaux horaires d'un médécin
    public List<Creneau> getAllCreneaux(Long idSalle);
    
    // liste des Rsv d'une salle, un jour donné
    public List<Rsv> getRsvSalleJour(Long idSalle, Date jour);

    // trouver un user identifié par son id
    public User getUserById(Long id);

    public Salle getSalleById(Long id);

    // trouver une Rsv identifié par son id
    public Rsv getRsvByid(Long id);

    // trouver un créneau identifié par son id
    public Creneau getCreneauById(Long id);

    // ajouter une Rsv
    public Rsv ajouterRsv(Date jour, Creneau creneau, User user);

    // supprimer une Rsv
    public void supprimerRsv(Long idRsv);

    // metier
    public AgendaSalleJour getAgendaSalleJour(Long idSalle, Date jour);
}
