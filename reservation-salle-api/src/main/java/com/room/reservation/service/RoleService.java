package com.room.reservation.service;

import com.room.reservation.model.Role;
import com.room.reservation.model.User;
import com.room.reservation.repository.RoleRepository;
import com.room.reservation.service.face.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role enregistrer(Role role) {
        Role res = null;
        try {
            res = roleRepository.save(role);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Role modifier(Role role) {
        return null;
    }

    @Override
    public void supprimer(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role getByName(String name) {
        Role res = null;
        try {
            res = roleRepository.findByName(name).get();
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Role> findAll() {
        List<Role> res = null;
        try {
            res = roleRepository.findAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
