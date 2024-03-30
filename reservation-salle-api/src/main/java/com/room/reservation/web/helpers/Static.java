package com.room.reservation.web.helpers;

import com.room.reservation.domain.AgendaSalleJour;
import com.room.reservation.domain.CreneauSalleJour;
import com.room.reservation.model.Creneau;
import com.room.reservation.model.Rsv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Static {
    public Static(){
    }

    public static List<String> getErreursForException(Exception e){
        Throwable cause = e;
        List<String> erreurs = new ArrayList<String>();
        while (cause != null){
            erreurs.add(cause.getMessage());
            cause = cause.getCause();
        }
        return erreurs;
    }

    // List<Creneau> --> List<Map>
    public static List<Map<String, Object>> getListMapForCreneaux(List<Creneau> creneaux) {
        // liste de dictionnaires <String,Object>
        List<Map<String, Object>> liste = new ArrayList<Map<String, Object>>();
        for (Creneau creneau : creneaux) {
            liste.add(Static.getMapForCreneau(creneau));
        }
        // on rend la liste
        return liste;
    }

    // List<Rsv> --> List<Map>
    public static List<Map<String, Object>> getListMapForRsvs(List<Rsv> rsvs) {
        // liste de dictionnaires <String,Object>
        List<Map<String, Object>> liste = new ArrayList<Map<String, Object>>();
        for (Rsv rsv : rsvs) {
            liste.add(Static.getMapForRsv(rsv));
        }
        // on rend la liste
        return liste;
    }

    public static Map<String, Object> getMapForRsv(Rsv rsv){
        if(rsv == null){
            return null;
        }
        // dictionnaire <String, Object>
        Map<String, Object> hash = new HashMap<String, Object>();
        hash.put("id", rsv.getId());
        hash.put("user", rsv.getUser());
        hash.put("creneau", getMapForCreneau(rsv.getCreneau()));

        // on rend le dictionnaire
        return hash;
    }

    public static Map<String, Object> getMapForCreneau(Creneau creneau){
        if(creneau == null){
            return null;
        }
        // dictionnaire <String, Object>
        Map<String, Object> hash = new HashMap<String, Object>();
        hash.put("id", creneau.getId());
        hash.put("hdebut", creneau.getHdebut());
        hash.put("hfin", creneau.getHfin());
        hash.put("mdebut", creneau.getMdebut());
        hash.put("mfin", creneau.getMfin());
        // on rend le dictionnaire
        return hash;
    }

    public static Map<String, Object> getMapForCreneauSalleJour(CreneauSalleJour creneau) {
         // qq chose à faire ?
        if (creneau == null) {
            return null;
        }
        // dictionnaire <String,Object>
        Map<String, Object> hash = new HashMap<String, Object>();
        hash.put("creneau", getMapForCreneau(creneau.getCreneau()));
        hash.put("rsv", getMapForRsv(creneau.getRsv()));
         // on rend le dictionnaire
        return hash;
    }

    public static Map<String, Object> getMapForAgendaSalleJour(AgendaSalleJour agenda) {
        // qq chose à faire ?
        if (agenda == null) {
            return null;
        }
        // dictionnaire <String,Object>
        Map<String, Object> hash = new HashMap<String, Object>();
        hash.put("salle", agenda.getSalle());
        hash.put("jour", new SimpleDateFormat("yyyy-MM-dd").format(agenda.getJour()));
        List<Map<String, Object>> creneaux = new ArrayList<Map<String, Object>>();
        for (CreneauSalleJour creneau: agenda.getCreneauSalleJour()) {
            creneaux.add(getMapForCreneauSalleJour(creneau));
        }
        hash.put("creneauxSalle", creneaux);
        // on rend le dictionnaire
        return hash;
    }
}
