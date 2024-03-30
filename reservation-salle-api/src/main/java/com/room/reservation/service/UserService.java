package com.room.reservation.service;

import com.room.reservation.model.User;
import com.room.reservation.repository.UserRepository;
import com.room.reservation.service.face.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User enregistrer(User user) {
        User res = null;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            res = userRepository.save(user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public User modifier(User user) {
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
}
