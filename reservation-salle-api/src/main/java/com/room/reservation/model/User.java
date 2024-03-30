package com.room.reservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(	name = "utilisateur")
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String email;
    private String username;
    private String password;
    private String contact;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;
}
