package com.room.reservation.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(	name = "creneaux")
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Creneau extends AbstractEntity{
    private static final long serialVersionUID = 1L;

    private int hdebut;
    private int hfin;
    private int mdebut;
    private int mfin;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_room")
    private Salle salle;
}
