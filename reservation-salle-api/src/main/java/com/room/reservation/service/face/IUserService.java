package com.room.reservation.service.face;

import com.room.reservation.model.User;
import com.room.reservation.service.IBaseService;

public interface IUserService extends IBaseService<User> {
    User getByUsername(String username);
}
