package com.room.reservation.domain;

import com.room.reservation.model.Salle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaSalleJour implements Serializable {
    private static final long serialVersionUID = 1L;

    // champs
    private Salle salle;
    private Date jour;
    private CreneauSalleJour[] creneauSalleJour;
}
