package com.room.reservation.model;

import jakarta.persistence.*;
import lombok.*;

@Table(	name = "salle")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salle extends AbstractEntity{
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
}
