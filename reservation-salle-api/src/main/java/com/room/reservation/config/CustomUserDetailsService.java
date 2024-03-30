package com.room.reservation.config;

import com.room.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        com.room.reservation.model.User res;
        try {
            res = userDao.findByUsername(username);
            if (res != null)
                user = new User(res.getUsername(), res.getPassword(), new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
