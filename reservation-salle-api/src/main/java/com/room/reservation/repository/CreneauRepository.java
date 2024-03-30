package com.room.reservation.repository;

import com.room.reservation.model.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreneauRepository extends JpaRepository<Creneau, Long> {
    @Query("select c from Creneau c where c.salle.id=:idSalle")
    List<Creneau> getAllCreneaux(Long idSalle);
}
