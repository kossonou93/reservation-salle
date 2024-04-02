package com.room.reservation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity{

    private static final long serialVersionUID = 1L;
    private String name;
}
