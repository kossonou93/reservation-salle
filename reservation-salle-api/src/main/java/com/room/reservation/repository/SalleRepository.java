package com.room.reservation.repository;

import com.room.reservation.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    Optional<Salle> findByName(String name);
}
