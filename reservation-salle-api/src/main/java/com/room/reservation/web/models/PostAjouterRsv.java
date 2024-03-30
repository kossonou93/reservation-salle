package com.room.reservation.web.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostAjouterRsv {
    // données du post
    private String jour;
    private Long idUser;
    private Long idCreneau;
}
