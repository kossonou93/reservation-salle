package com.room.reservation.controller;

import com.room.reservation.config.JwtService;
import com.room.reservation.dto.AuthRequestDTO;
import com.room.reservation.dto.JwtResponseDTO;
import com.room.reservation.model.User;
import com.room.reservation.service.IBaseService;
import com.room.reservation.service.face.IUserService;
import com.room.reservation.web.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = { "*" }, exposedHeaders = { "Content-Disposition" })
@RequestMapping(path = "/api/user")
public class UserController extends BaseController<User>{

    @Autowired
    private IUserService iUserService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public UserController(IBaseService<User> baseService){
        super(baseService);
    }

    @PostMapping("/login")
    public JwtResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
            );
            System.out.println("authentication ===> " + authentication);
            if (authentication.isAuthenticated()) {
                return JwtResponseDTO.builder().accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
            } else {
                throw new UsernameNotFoundException("Invalid user credentials.");
            }
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Invalid user credentials.", e);
        }
    }

    /*@PostMapping("/resgister")
    public User register(@RequestBody User user){
        return iUserService.enregistrer(user);
    }*/
}
