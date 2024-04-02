package com.room.reservation.service.face;

import com.room.reservation.dto.UserDTO;
import com.room.reservation.model.User;
import com.room.reservation.service.IBaseService;

public interface IUserService extends IBaseService<UserDTO> {
    User getByUsername(String username);
    UserDTO convertEntityToDTO(User user);
    User convertDtoToEntity(UserDTO userDTO);
}
