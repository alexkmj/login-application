package com.login.application.loginapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

/**
 * This class is used to configure the security of the application.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	/**
	 * The UserDetailsService object.
	 */
	private final UserDetailsService userDetailsService;

	/**
	 * Constructor.
	 * 
	 * @param userDetailsService the UserDetailsService object
	 */
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * This method is used to configure the security of the application. It is
	 * used to configure the authentication and authorization of the
	 * application.
	 * 
	 * @param http the HttpSecurity object
	 * @return the SecurityFilterChain object
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		http
				.authorizeHttpRequests()
				.requestMatchers("/signup", "/js/signup.js").anonymous()
				.requestMatchers(toH2Console()).permitAll()
				.requestMatchers("/", "/js/app.js", "/api/v1/user/loggedin").permitAll()
				.requestMatchers("/restricted", "/js/restricted.js", "/api/v1/users").hasRole("MANAGER")
				.requestMatchers("/api/v1/register").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.defaultSuccessUrl("/welcome", true)
				.and()
				.csrf().disable()
				.authenticationProvider(authenticationProvider());;

		// development only
		http.headers().frameOptions().sameOrigin();

		return http.build();
	}

	/**
	 * This method is used to configure the authentication provider.
	 * 
	 * @return the AuthenticationProvider object
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	/**
	 * This method is used to configure the password encoder.
	 * 
	 * @return the PasswordEncoder object
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
