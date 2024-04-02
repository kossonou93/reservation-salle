package com.room.reservation.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String contact;

    private List<String> roles;
}
