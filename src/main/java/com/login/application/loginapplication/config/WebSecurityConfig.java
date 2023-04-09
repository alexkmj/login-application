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
 * Configures the security of the application. This class is used to configure
 * the security filter chain and the authentication provider.
 * 
 * The security filter chain is used to configure the security of the
 * application. The {@link Configuration} annotation is used to specify that
 * this class is a configuration class.
 * 
 * The authentication provider is used to configure the authentication
 * mechanism. The {@link EnableWebSecurity} annotation is used to enable Spring
 * Security for the application.
 * 
 * @author Alex Koh
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	/**
	 * The user details service used for authentication.
	 */
	private final UserDetailsService userDetailsService;

	/**
	 * Constructs a new WebSecurityConfig object. 
	 * 
	 * @param userDetailsService the user details service used for authentication
	 */
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
     * Configures the security filter chain for the application.
	 * 
	 * The {@link @Bean} annotation is used to mark this method as a bean. This
	 * bean is used by Spring to create an instance of this object.
     *
     * @param http the HttpSecurity object
     * @return the SecurityFilterChain object
     * @throws Exception if an error occurs during configuration
     */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		http
				.authorizeHttpRequests()
				.requestMatchers("/signup", "/js/signup.js").anonymous()
				.requestMatchers(toH2Console()).permitAll()
				.requestMatchers("/", "/js/home.js", "/js/custom-app-bar.js").permitAll()
				.requestMatchers("/restricted", "/js/restricted.js").hasRole("MANAGER")
				.requestMatchers("/api/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.defaultSuccessUrl("/welcome", true)
				.and()
				.csrf().disable() // TODO: Remove this in production environment
				.authenticationProvider(authenticationProvider());

		// Allow H2 console to be displayed in development environment
		// TODO: Remove this in production environment
		http.headers().frameOptions().sameOrigin();

		return http.build();
	}

	/**
     * Configures the authentication provider for the application.
	 * 
	 * @{link DaoAuthenticationProvider} is used to authenticate users using a
	 * database.
	 * 
	 * The {@link @Bean} annotation is used to mark this method as a bean. This
	 * bean is used by Spring to create an instance of this object.
     *
     * @return the DaoAuthenticationProvider object
     */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	/**
     * Configures the password encoder for the application.
	 * 
	 * @{link BCryptPasswordEncoder} is used to encode passwords using the
	 * BCrypt hashing algorithm.
	 * 
	 * The {@link @Bean} annotation is used to mark this method as a bean. This
	 * bean is used by Spring to create an instance of this object.
     *
     * @return the BCryptPasswordEncoder object
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
