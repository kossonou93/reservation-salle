package com.room.reservation.repository;

import com.room.reservation.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, Long> {
}
