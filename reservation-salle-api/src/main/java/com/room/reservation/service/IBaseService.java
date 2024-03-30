package com.room.reservation.service;

public interface IBaseService<T> {
    T enregistrer(T t);

    T modifier(T t);

    void supprimer(Long id);
}
