package com.room.reservation.repository;

import com.room.reservation.model.Rsv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface RsvRepository extends JpaRepository<Rsv, Long> {
    @Query("select rsv from Rsv rsv left join fetch rsv.user u left join rsv.creneau cr where cr.salle.id=?1 and rsv.jour=?2")
    Iterable<Rsv> getRsvSalleJour(long idSalle, Date jour);
}
