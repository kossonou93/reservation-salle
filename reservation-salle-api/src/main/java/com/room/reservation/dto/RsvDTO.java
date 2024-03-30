package com.room.reservation.dto;

import com.room.reservation.model.Creneau;
import com.room.reservation.model.User;

import java.util.Date;

public record RsvDTO(Long id, Date jour, User user, Creneau creneau) {
}
