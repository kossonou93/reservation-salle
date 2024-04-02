package com.room.reservation.repository;

import com.room.reservation.model.ERole;
import com.room.reservation.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //Role findByName(String name);

    Optional<Role> findByName(String name);
}
