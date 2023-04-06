package com.login.application.loginapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.login.application.loginapplication.models.User;
import com.login.application.loginapplication.repositories.UserRepository;

@SpringBootApplication
public class LoginApplication {

	/**
	 * The main method.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	/**
	 * Command line runner. This method is used to create the admin and user
	 * accounts.
	 * 
	 * @param users the users
	 * @return the command line runner
	 */
	@Bean
	CommandLineRunner commandLineRunner(UserRepository users) {
		if (!users.existsByUsername("admin")) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			String encodedPassword = passwordEncoder.encode("password");
			User admin = new User("admin", encodedPassword, "Administrator", true);
			User user = new User("user", encodedPassword, "User", false);
			return args -> {
				users.save(admin);
				users.save(user);
			};
		}

		return args -> {
		};

	}
}
