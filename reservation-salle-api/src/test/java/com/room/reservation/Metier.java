package com.room.reservation;

import com.room.reservation.domain.AgendaSalleJour;
import com.room.reservation.model.Creneau;
import com.room.reservation.model.Rsv;
import com.room.reservation.model.Salle;
import com.room.reservation.model.User;
import com.room.reservation.repository.SalleRepository;
import com.room.reservation.repository.UserRepository;
import com.room.reservation.service.IMetier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Metier {

    @Autowired
    private IMetier metier;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Test
    public void test1(){

        //Enregistrer un user
        User user1 = new User();
        user1.setEmail("admin@gmail.com");
        user1.setUsername("admin");
        user1.setContact("08029302");
        user1.setVersion(1L);

        // affichage users
        List<User> users = metier.getAllUsers();
        display("Liste des users : " , users);

        //Enregistrer une salle
        Salle salle1 = new Salle();
        salle1.setName("Salle 1");
        salle1.setDescription("La prémière salle");
        salle1.setVersion(1L);

        // Enregistrer des créneaux
        Creneau creneau1 = new Creneau();
        creneau1.setHdebut(7);
        creneau1.setHfin(11);
        creneau1.setMdebut(0);
        creneau1.setMfin(0);
        creneau1.setSalle(salle1);

        Creneau creneau2 = new Creneau();
        creneau2.setHdebut(11);
        creneau2.setHfin(15);
        creneau2.setMdebut(0);
        creneau2.setMfin(0);
        creneau2.setSalle(salle1);

        Creneau creneau3 = new Creneau();
        creneau3.setHdebut(15);
        creneau3.setHfin(18);
        creneau3.setMdebut(0);
        creneau3.setMfin(0);
        creneau3.setSalle(salle1);

        // affichage liste salles
        List<Salle> salles = metier.getAllSalles();
        display("Liste des salles : ", salles);

        //affichage créneaux d'une salle
        Salle salle = salles.get(0);
        List<Creneau> creneaux = metier.getAllCreneaux(salle.getId());
        display(String.format("Liste des créneaux de la salle %s ", salle) , creneaux);

        // Liste des Rsv d'une salle, un jour donné
        Date jour = new Date();
        display(String.format("Liste des rsv de la salle %s, le [%s]", salle , jour) , metier.getRsvSalleJour(salle.getId(), jour));

        // Ajouter un RSV
        Rsv rsv = null;
        System.out.println("Listes de Créneaux ====> " + creneaux);
        Creneau creneau = creneaux.get(0);
        User user = users.get(0);

        System.out.println(String.format("Ajout d'un RSV le [%s] dans le créneau %s pour le user %s", jour , creneau, user));

        rsv = metier.ajouterRsv(jour, creneau, user);
        // vérification
        Rsv rsv1 = metier.getRsvByid(rsv.getId());
        Assert.assertEquals(rsv, rsv1);
        display(String.format("Liste des Rsv de la salle %s, le [%s]", salle, jour) , metier.getRsvSalleJour(salle.getId(), jour));

        // ajouter un RSV dans le même créneau du même jour
        // doit provoquer une exception
        System.out.println(String.format("Ajout d'un RSV le [%s] dans le créneau %s pour le user %s", jour, creneau, user));

        Boolean erreur = false;
        try {
            rsv = metier.ajouterRsv(jour, creneau, user);
            System.out.println("RSV ajouté");
        } catch (Exception ex){
            Throwable th = ex;
            while (th != null){
                System.out.println(ex.getMessage());
                th = th.getCause();
            }
            erreur = true;
        }

        // on vérifie qu'il n'y a pas d'erreur
        System.out.println("erreur ==> " + erreur);
        Assert.assertFalse(erreur);

        // liste RSV
        display(String.format("Liste des RSV de la salle %s, le [%s]", salle,  jour), metier.getRsvSalleJour(salle.getId(), jour));

        // Affichage agenda
        AgendaSalleJour agenda = metier.getAgendaSalleJour(salle.getId(), jour);
        System.out.println(agenda);

        // supprimer un RSV
        System.out.println("Suppression du RSV ajouté");
        metier.supprimerRsv(rsv.getId());

        rsv1 = metier.getRsvByid(rsv.getId());
        System.out.println("res1 ==> " + rsv1);
        Assert.assertNotNull(rsv1);
        display(String.format("Liste des RSV de la salle %s, le [%s]", salle, jour), metier.getRsvSalleJour(salle.getId(), jour));
    }

    // méthode display, utilitaire - affiche les éléments d'une collection
    private void
    display(String message, Iterable<?> elements){
        System.out.println(message);
        for (Object element: elements) {
            System.out.println(element);
        }
    }
}
