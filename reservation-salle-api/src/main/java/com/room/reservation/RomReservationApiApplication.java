package com.room.reservation;

import com.room.reservation.dto.UserDTO;
import com.room.reservation.model.*;
import com.room.reservation.repository.CreneauRepository;
import com.room.reservation.repository.RoleRepository;
import com.room.reservation.repository.SalleRepository;
import com.room.reservation.repository.UserRepository;
import com.room.reservation.service.face.IUserService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.webjars.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RomReservationApiApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private CreneauRepository creneauRepository;

	@Autowired
	private IUserService iuserService;

	@Autowired
	private RoleRepository roleRepository;

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

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
		if (!roleRepository.findByName("ROLE_ADMIN").isPresent()){
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);
		}

		Role roleUser = new Role();
		if (!roleRepository.findByName("ROLE_USER").isPresent()){
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);
		}
	}

	public void createUser() throws NotFoundException{
		UserDTO userDTO = new UserDTO();
		if (!userRepository.findByEmail("koffi@gmail.com").isPresent()) {
			List<String> roles = new ArrayList<>();
			roles.add("ROLE_USER");
			userDTO.setEmail("koffi@gmail.com");
			userDTO.setUsername("koffi");
			userDTO.setPassword("koffi");
			//user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDTO.setContact("0245120021");
			userDTO.setRoles(roles);

			System.out.println("user ==> " + userDTO);
			iuserService.enregistrer(userDTO);
		}

		UserDTO adminDTO = new UserDTO();
		if (!userRepository.findByEmail("admin@gmail").isPresent()) {
			List<String> roles = new ArrayList<>();
			roles.add("ROLE_ADMIN");
			adminDTO.setEmail("admin@gmail");
			adminDTO.setUsername("admin");
			adminDTO.setPassword("admin");
			//admin.setPassword(passwordEncoder.encode(user.getPassword()));
			adminDTO.setContact("0587545232");
			adminDTO.setRoles(roles);
			iuserService.enregistrer(adminDTO);
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
			salle3.setName("Salle 3");
			salle3.setDescription("La troisième salle");
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
		if (!creneauRepository.findById(2L).isPresent()){
			creneau2.setSalle(salleRepository.findByName("Salle 1").get());
			creneau2.setHdebut(1);
			creneau2.setHfin(2);
			creneau2.setMdebut(0);
			creneau2.setMfin(0);
			creneauRepository.save(creneau2);
		}

		Creneau creneau3 = new Creneau();
		if (!creneauRepository.findById(3L).isPresent()){
			creneau3.setSalle(salleRepository.findByName("Salle 2").get());
			creneau3.setHdebut(0);
			creneau3.setHfin(1);
			creneau3.setMdebut(0);
			creneau3.setMfin(0);
			creneauRepository.save(creneau3);
		}

		Creneau creneau4 = new Creneau();
		if (!creneauRepository.findById(4L).isPresent()){
			creneau4.setSalle(salleRepository.findByName("Salle 3").get());
			creneau4.setHdebut(0);
			creneau4.setHfin(1);
			creneau4.setMdebut(0);
			creneau4.setMfin(0);
			creneauRepository.save(creneau4);
		}

		Creneau creneau5 = new Creneau();
		if (!creneauRepository.findById(5L).isPresent()){
			creneau5.setSalle(salleRepository.findByName("Salle 1").get());
			creneau5.setHdebut(3);
			creneau5.setHfin(4);
			creneau5.setMdebut(0);
			creneau5.setMfin(0);
			creneauRepository.save(creneau5);
		}
	}

}
