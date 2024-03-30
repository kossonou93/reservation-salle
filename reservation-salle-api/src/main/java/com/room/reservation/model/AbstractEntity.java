package com.room.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Version
    protected Long version;

    @Override
    public int hashCode(){
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public AbstractEntity build(Long id, Long version){
        this.id = id;
        this.version = version;
        return this;
    }

    @Override
    public boolean equals(Object entity){
        String class1 = this.getClass().getName();
        String class2 = entity.getClass().getName();
        if (!class2.equals(class1)){
            return false;
        }
        AbstractEntity other = (AbstractEntity) entity;
        return this.id == other.id;
    }

}