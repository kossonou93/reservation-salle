package com.room.reservation;

import com.room.reservation.model.*;
import com.room.reservation.repository.CreneauRepository;
import com.room.reservation.repository.RoleRepository;
import com.room.reservation.repository.SalleRepository;
import com.room.reservation.repository.UserRepository;
import com.room.reservation.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RomReservationApiApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private CreneauRepository creneauRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() throws NotFoundException{
		createRole();
		//createUser();
		createSalle();
		createCreneau();
	}

	public static void main(String[] args) {
		SpringApplication.run(RomReservationApiApplication.class, args);
	}

	public void createRole(){
		Role roleAdmin = new Role();
		if (!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()){
			roleAdmin.setName(ERole.ROLE_ADMIN);
			roleRepository.save(roleAdmin);
		}

		Role roleUser = new Role();
		if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()){
			roleAdmin.setName(ERole.ROLE_USER);
			roleRepository.save(roleUser);
		}
	}

	public void createUser() throws NotFoundException{
		User user = new User();
		if (!userRepository.findByEmail("koffi@gmail.com").isPresent()) {
			List<Role> roles = new ArrayList<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
			roles.add(userRole);
			user.setEmail("koffi@gmail.com");
			user.setUsername("koffi");
			user.setPassword("koffi");
			user.setPassword("koffi");
			//user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setContact("0245120021");
			//user.setRoles(roles);
			System.out.println("user ==> " + user);
			userService.enregistrer(user);
		}

		User admin = new User();
		if (!userRepository.findByEmail("admin@gmail").isPresent()) {
			List<Role> roles = new ArrayList<>();
			admin.setEmail("admin@gmail");
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin.setPassword(passwordEncoder.encode(user.getPassword()));
			admin.setContact("0587545232");
			roles.add(roleRepository.findById(2L).get());
			//admin.setRoles(roles);
			userService.enregistrer(admin);
		}
	}

	public void createSalle(){
		Salle salle1 = new Salle();
		if (!salleRepository.findByName("Salle 1").isPresent()){
			salle1.setName("Salle 1");
			salle1.setDescription("La prémière salle");
			salleRepository.save(salle1);
		}

		Salle salle2 = new Salle();
		if (!salleRepository.findByName("Salle 2").isPresent()){
			salle2.setName("Salle 2");
			salle2.setDescription("La deuxième salle");
			salleRepository.save(salle2);
		}

		Salle salle3 = new Salle();
		if (!salleRepository.findByName("Salle 3").isPresent()){
			salle1.setName("Salle 3");
			salle1.setDescription("La troisième salle");
			salleRepository.save(salle3);
		}
	}

	public void createCreneau(){
		Creneau creneau1 = new Creneau();
		if (!creneauRepository.findById(1L).isPresent()){
			creneau1.setSalle(salleRepository.findByName("Salle 1").get());
			creneau1.setHdebut(0);
			creneau1.setHfin(1);
			creneau1.setMdebut(0);
			creneau1.setMfin(0);
			creneauRepository.save(creneau1);
		}

		Creneau creneau2 = new Creneau();
		if (!creneauRepository.findById(1L).isPresent()){
			creneau2.setSalle(salleRepository.findByName("Salle 1").get());
			creneau2.setHdebut(1);
			creneau2.setHfin(2);
			creneau2.setMdebut(0);
			creneau2.setMfin(0);
			creneauRepository.save(creneau2);
		}

		Creneau creneau3 = new Creneau();
		if (!creneauRepository.findById(1L).isPresent()){
			creneau3.setSalle(salleRepository.findByName("Salle 2").get());
			creneau3.setHdebut(0);
			creneau3.setHfin(1);
			creneau3.setMdebut(0);
			creneau3.setMfin(0);
			creneauRepository.save(creneau3);
		}

		Creneau creneau4 = new Creneau();
		if (!creneauRepository.findById(1L).isPresent()){
			creneau4.setSalle(salleRepository.findByName("Salle 3").get());
			creneau4.setHdebut(0);
			creneau4.setHfin(1);
			creneau4.setMdebut(0);
			creneau4.setMfin(0);
			creneauRepository.save(creneau4);
		}

		Creneau creneau5 = new Creneau();
		if (!creneauRepository.findById(1L).isPresent()){
			creneau5.setSalle(salleRepository.findByName("Salle 1").get());
			creneau5.setHdebut(3);
			creneau5.setHfin(4);
			creneau5.setMdebut(0);
			creneau5.setMfin(0);
			creneauRepository.save(creneau5);
		}
	}

}
