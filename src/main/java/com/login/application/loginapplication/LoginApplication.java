package com.login.application.loginapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.login.application.loginapplication.models.User;
import com.login.application.loginapplication.repositories.UserRepository;

/**
 * The main class for the Login Application. This class contains the
 * {@code main} method and the command line runner bean that initializes the
 * application with admin and user accounts.
 * 
 * The {@code @SpringBootApplication} annotation is used to specify that this
 * class is the main class for the application.
 * 
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * 
 * @author Alex Koh
 */
@SpringBootApplication
public class LoginApplication {

	/**
	 * The main method. Starts the Spring Boot application.
	 * 
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	/**
	 * The command line runner method. This method is used to create the admin
	 * and user accounts.
	 * 
	 * The {@code @Bean} annotation is used to specify that this method is a
	 * bean. A bean is an object that is created by Spring and managed by
	 * Spring. Beans are used to perform tasks such as initializing the database
	 * with data.
	 * 
	 * @param users The user repository.
	 * @return The command line runner.
	 */
	@Bean
	CommandLineRunner commandLineRunner(UserRepository users) {
		// check if the admin account exists
		if (!users.existsByUsername("admin")) {
			// create the admin account
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode("password");

			User admin = new User("admin", encodedPassword, "Administrator", true);
			User user = new User("user", encodedPassword, "User", false);

			return args -> {
				users.save(admin);
				users.save(user);
			};
		}

		// return an empty command line runner
		return args -> {};
	}
}
