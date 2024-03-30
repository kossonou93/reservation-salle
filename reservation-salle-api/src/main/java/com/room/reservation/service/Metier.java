package com.room.reservation.service;

import com.google.common.collect.Lists;
import com.room.reservation.domain.AgendaSalleJour;
import com.room.reservation.domain.CreneauSalleJour;
import com.room.reservation.model.Creneau;
import com.room.reservation.model.Rsv;
import com.room.reservation.model.Salle;
import com.room.reservation.model.User;
import com.room.reservation.repository.CreneauRepository;
import com.room.reservation.repository.RsvRepository;
import com.room.reservation.repository.SalleRepository;
import com.room.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service
public class Metier implements IMetier{

    // Repositories
    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreneauRepository creneauRepository;

    @Autowired
    private RsvRepository rsvRepository;

    @Override
    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public List<Salle> getAllSalles() {
        return Lists.newArrayList(salleRepository.findAll());
    }

    @Override
    public List<Creneau> getAllCreneaux(Long idSalle) {
        return Lists.newArrayList(creneauRepository.getAllCreneaux(idSalle));
    }

    @Override
    public List<Rsv> getRsvSalleJour(Long idSalle, Date jour) {
        return Lists.newArrayList(rsvRepository.getRsvSalleJour(idSalle, jour));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Salle getSalleById(Long id) {
        return salleRepository.findById(id).get();
    }

    @Override
    public Rsv getRsvByid(Long id) {
        return rsvRepository.findById(id).get();
    }

    @Override
    public Creneau getCreneauById(Long id) {
        return creneauRepository.findById(id).get();
    }

    @Override
    public Rsv ajouterRsv(Date jour, Creneau creneau, User user) {
        return rsvRepository.save(new Rsv(jour, user, creneau));
    }

    @Override
    public void supprimerRsv(Long idRsv) {

    }

    @Override
    public AgendaSalleJour getAgendaSalleJour(Long idSalle, Date jour) {
        // Liste des créneaux horaires du médécin
        List<Creneau> creneauxHoraires = getAllCreneaux(idSalle);
        // liste des réservations de cette même salle pour ce même jour
        List<Rsv> reservations = getRsvSalleJour(idSalle, jour);
        //on crée un dictionnaire à partir des Rsv pris
        Map<Long, Rsv> hReservations = new Hashtable<Long, Rsv>();
        for (Rsv resa: reservations) {
            hReservations.put(resa.getCreneau().getId(), resa);
        }
        // on crée l'agenda pour le jour demandé
        AgendaSalleJour agenda = new AgendaSalleJour();
        // La salle
        agenda.setSalle(getSalleById(idSalle));
        //le jour
        agenda.setJour(jour);
        //les créneaux de réservation
        CreneauSalleJour[] creneauSalleJour = new CreneauSalleJour[creneauxHoraires.size()];
        agenda.setCreneauSalleJour(creneauSalleJour);
        //remplissage des créneaux de réservations
        for (int i = 0; i < creneauxHoraires.size(); i++) {
            // ligne i agenda
            creneauSalleJour[i] = new CreneauSalleJour();
            // créneau horaire
            Creneau creneau = creneauxHoraires.get(i);
            long idCreneau = creneau.getId();
            creneauSalleJour[i].setCreneau(creneau);
            if (hReservations.containsKey(idCreneau)){
                Rsv resa = hReservations.get(idCreneau);
                creneauSalleJour[i].setRsv(resa);
            }
        }
        return agenda;
    }
}
