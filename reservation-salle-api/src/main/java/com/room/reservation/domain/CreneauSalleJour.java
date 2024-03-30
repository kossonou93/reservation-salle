package com.room.reservation.domain;

import com.room.reservation.model.Creneau;
import com.room.reservation.model.Rsv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreneauSalleJour implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // champs
    private Creneau creneau;
    private Rsv rsv;
}
