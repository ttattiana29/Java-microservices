package ru.itmo.kotikilab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itmo.kotikilab.entities.*;
import ru.itmo.kotikilab.services.MainService;

import java.time.LocalDate;

@SpringBootApplication
public class KotikiLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(KotikiLabApplication.class, args);
	}

	@Bean
	CommandLineRunner run(MainService mainService) {
		return args -> {
			Owner owner = new Owner("Masha", LocalDate.of(2002, 03, 29), "username", "1234", "user");
			mainService.saveOwner(owner);
			Kotik kotik = new Kotik("Rubi", LocalDate.of(2022, 03, 29), "No name", Color.GREY, owner);
			kotik.setOwnerId(owner);
			mainService.saveKotik(kotik);
			//mainService.addOwnerToKotik(owner.getId(), kotik.getId());

			Owner owner2 = new Owner("Tanya", LocalDate.of(2002, 05, 29), "username2", "1234", "admin");
			mainService.saveOwner(owner2);
			Kotik kotik2 = new Kotik("Vudi", LocalDate.of(2022, 03, 29), "No name", Color.WHITE, owner);
			kotik2.setOwnerId(owner2);
			mainService.saveKotik(kotik2);
			//mainService.addOwnerToKotik(owner2.getId(), kotik2.getId());

			/*Friend friend = mainService.addFriends(kotik.getId(), kotik2.getId());
			mainService.saveFriend(friend);*/
		};
	}
}
