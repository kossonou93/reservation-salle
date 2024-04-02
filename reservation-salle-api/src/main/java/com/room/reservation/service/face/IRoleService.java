package com.room.reservation.service.face;

import com.room.reservation.model.Role;
import com.room.reservation.service.IBaseService;

import java.util.List;

public interface IRoleService extends IBaseService<Role> {
    Role getByName(String name);

    List<Role> findAll();
}
