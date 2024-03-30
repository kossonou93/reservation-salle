package com.room.reservation.dto;

import com.room.reservation.model.Salle;

public record CreneauDTO(Long id, int hdebut, int hfin, int mdebut, int mfin, Salle salle) {
}
