package com.room.reservation.controller;

import com.room.reservation.domain.AgendaSalleJour;
import com.room.reservation.model.Creneau;
import com.room.reservation.model.Rsv;
import com.room.reservation.model.Salle;
import com.room.reservation.model.User;
import com.room.reservation.web.helpers.Static;
import com.room.reservation.web.models.ApplicationModel;
import com.room.reservation.web.models.PostAjouterRsv;
import com.room.reservation.web.models.PostSupprimerRsv;
import com.room.reservation.web.models.Response;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = { "*" }, exposedHeaders = { "Content-Disposition" })
@RequestMapping("/api/rsv")
public class RsvSalleController {
    @Autowired
    private ApplicationModel applicationModel;
    private List<String> messages;

    @PostConstruct
    public void init(){
        // messages d'erreur de l'application
        messages = applicationModel.getMessages();
    }

    // liste des salles
    @GetMapping(value = "/salles")
    public Response getAllSalle(){
       return new Response(200, applicationModel.getAllSalles());
    }

    // liste des users
    @GetMapping(value = "/users")
    public Response getAllUser(){
        // état de l'application
        if (messages != null){
            return new Response(-1, messages);
        }
        //on récupère le user
        List<User> users = null;
        try {
            users = applicationModel.getAllUsers();
        } catch (Exception e1) {
            return new Response(1, Static.getErreursForException(e1));
        }
        // users existant ?
        if (users == null) {
            return new Response(2, null);
        }
        return new Response(0, users);
    }

    // liste des créneaux
    @GetMapping(value = "/creneaux/{idSalle}")
    public Response getAllCrenaux(@PathVariable("idSalle") Long idSalle){
        // état de l'application
        if (messages != null){
            return new Response(-1, messages);
        }
        //on récupère le creneau
        List<Creneau> creneaux = null;
        try {
            creneaux = applicationModel.getAllCreneaux(idSalle);
        } catch (Exception e1) {
            return new Response(1, Static.getErreursForException(e1));
        }
        // creneau existant ?
        if (creneaux == null) {
            return new Response(2, null);
        }
        return new Response(0, creneaux);
    }

    // un creneau
    @GetMapping(value = "/creneau/{id}")
    public Response getCreneauById(@PathVariable("id") Long id){
        // état de l'application
        if (messages != null){
            return new Response(-1, messages);
        }
        //on récupère le creneau
        Creneau creneau = null;
        try {
            creneau = applicationModel.getCreneauById(id);
        } catch (Exception e1) {
            return new Response(1, Static.getErreursForException(e1));
        }
        // creneau existant ?
        if (creneau == null) {
            return new Response(2, null);
        }
        return new Response(0, creneau);
    }

    // trouver un user par son id
    @GetMapping(value = "/user/{id}")
    public Response getUserById(@PathVariable("id") Long id){
        // état de l'application
        if (messages != null){
            return new Response(-1, messages);
        }
        //on récupère le user
        User user = null;
        try {
            user = applicationModel.getUserById(id);
        } catch (Exception e1) {
            return new Response(1, Static.getErreursForException(e1));
        }
        // user existant ?
        if (user == null) {
            return new Response(2, null);
        }
        return new Response(0, user);
    }

    // trouver une salle par son id
    @GetMapping(value = "/salle{id}")
    public Response getSalleById(@PathVariable("id") Long id){
        // état de l'application
        if (messages != null){
            return new Response(-1, messages);
        }
        //on récupère la salle
        Salle salle = null;
        try {
            salle = applicationModel.getSalleById(id);
        } catch (Exception e1) {
            return new Response(1, Static.getErreursForException(e1));
        }
        // médecin existant ?
        if (salle == null) {
            return new Response(2, null);
        }
        return new Response(0, salle);
    }

    // trouver une rsv par son id
    @GetMapping(value = "/{id}")
    public Response getRsvById(@PathVariable("id") Long id){
        return new Response(200, applicationModel.getRsvByid(id));
    }

    // liste des Rsv d'une salle
    @GetMapping(value = "/rsv-salle/{idSalle}/{jour}")
    public Response getRsvSalleJour(@PathVariable("idSalle") Long idSalle, @PathVariable("jour") String jour){
        if(messages != null){
            return new Response(-1, messages);
        }

        // on vérifie la date
        Date jourAgenda = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            jourAgenda = sdf.parse(jour);
        } catch (ParseException e) {
            return new Response(3, null);
        }
        //on récupère la salle
        Response response = getSalleById(idSalle);
        if (response.getStatus() != 0){
            return response;
        }

        Salle salle = (Salle) response.getObject();
        List<Rsv> rsvs = null;
        try {
            rsvs = applicationModel.getRsvSalleJour(salle.getId(), jourAgenda);
        } catch (Exception e){
            return new Response(4, Static.getErreursForException(e));
        }
        // on rend la réponse
        return new Response(0, Static.getListMapForRsvs(rsvs));
    }

    @GetMapping(value = "/agenda-salle/{idSalle}/{jour}")
    public Response getAgendaMedecinJour(@PathVariable("idSalle") long idSalle, @PathVariable("jour") String jour) {
         // état de l'application
         if (messages != null) {
             return new Response(-1, messages);
             }
         // on vérifie la date
         Date jourAgenda = null;
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         sdf.setLenient(false);
         try {
             jourAgenda = sdf.parse(jour);
             } catch (ParseException e) {
             return new Response(3, new String[] { String.format("jour [%s] invalide", jour) });
             }
         // on récupère la salle
        Response reponse = getSalleById(idSalle);
         if (reponse.getStatus() != 0) {
             return reponse;
             }
         Salle salle = (Salle) reponse.getObject();
         // on récupère son agenda
         AgendaSalleJour agenda = null;
         try {
             agenda = applicationModel.getAgendaSalleJour(salle.getId(), jourAgenda);
             } catch (Exception e) {
                return new Response(4, Static.getErreursForException(e));
            }
         return new Response(0, Static.getMapForAgendaSalleJour(agenda));
    }

    @PostMapping(value = "/ajouter-rsv")
             public Response ajouterRv(@RequestBody PostAjouterRsv post) {
             // état de l'application
             if (messages != null) {
             return new Response(-1, messages);
             }
             // on récupère les valeurs postées
        System.out.println("la valeur de post ==> " + post);
             String jour = post.getJour();
             Long idCreneau = post.getIdCreneau();
             Long idUser = post.getIdUser();
             // on vérifie la date
             Date jourAgenda = null;
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             sdf.setLenient(false);
             try {
             jourAgenda = sdf.parse(jour);
             } catch (ParseException e) {
         return new Response(6, null);
         }
 // on récupère le créneau
        Response reponse = getCreneauById(idCreneau);
 if (reponse.getStatus() != 0) {
         return reponse;
         }
 Creneau creneau = (Creneau) reponse.getObject();
 // on récupère le user
        reponse = getUserById(idUser);
 if (reponse.getStatus() != 0) {
     reponse.incrStatusBy(2);
        return reponse;
         }
 User user = (User) reponse.getObject();
 // on ajoute le Rsv
         Rsv rsv = null;
 try {
         rsv = applicationModel.ajouterRsv(jourAgenda, creneau, user);
         } catch (Exception e) {
         return new Response(5, Static.getErreursForException(e));
         }
         // on rend la réponse
         return new Response(0, Static.getMapForRsv(rsv));
     }

        @PostMapping(value = "/supprimer-rsv")
            public Response supprimerRv(@RequestBody PostSupprimerRsv post) {
             // état de l'application
             if (messages != null) {
             return new Response(-1, messages);
             }
             // on récupère les valeurs postées
             Long idRsv = post.getIdRsv();
             // on récupère la rsv
             Response reponse = getRsvById(idRsv);
             if (reponse.getStatus() != 0) {
             return reponse;
             }
             // suppression du rsv
             try {
             applicationModel.supprimerRsv(idRsv);
             } catch (Exception e) {
         return new Response(3, Static.getErreursForException(e));
         }
         return new Response(0, null);
        }
}

