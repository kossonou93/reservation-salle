package com.room.reservation.web.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int status;
    private Object object;

    public void incrStatusBy(int increment){
        status += increment;
    }
}
