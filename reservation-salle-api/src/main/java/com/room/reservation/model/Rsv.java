package com.room.reservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(	name = "rsv")
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Rsv extends AbstractEntity{
    private static final long serialVersionUID = 1L;

    // caractéristique d'un RDV
    @Temporal(TemporalType.DATE)
    private Date jour;

    // un RDV est lié à un user
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    // un RDV est lié à un créneau
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_creneau")
    private Creneau creneau;
}
