package com.room.reservation.service;

import com.room.reservation.dto.UserDTO;
import com.room.reservation.model.Role;
import com.room.reservation.model.User;
import com.room.reservation.repository.RoleRepository;
import com.room.reservation.repository.UserRepository;
import com.room.reservation.service.face.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO enregistrer(UserDTO userDTO) {
        User res = null;
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = convertDtoToEntity(userDTO);
        try {
            res = userRepository.save(user);
        } catch (Exception e){
            e.printStackTrace();
        }

        return convertEntityToDTO(res);
    }

    @Override
    public UserDTO modifier(UserDTO userDTO) {
        return null;
    }

    @Override
    public void supprimer(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByUsername(String username) {
        User res = null;
        try {
            res = userRepository.findByUsername(username);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public UserDTO convertEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setContact(user.getContact());
        userDTO.setPassword(user.getPassword());
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        userDTO.setRoles(roles);
        return userDTO;
    }

    @Override
    public User convertDtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setContact(userDTO.getContact());
        user.setPassword(userDTO.getPassword());
        List<Role> roles = new ArrayList<>();
        for (String role: userDTO.getRoles()) {
            Role existingRole = roleRepository.findByName(role).get();
            if (existingRole == null){
                throw new IllegalArgumentException("Le role " + role + " n'existe pas.");
            }
            roles.add(existingRole);
        }
        user.setRoles(roles);
        return user;
    }
}
