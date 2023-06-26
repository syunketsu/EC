package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.formLogin(login -> login
				.loginPage("/login")
				.defaultSuccessUrl("/page")
				.usernameParameter("userName")
				.passwordParameter("userPassword")
				.failureUrl("/loginerror")
				.loginProcessingUrl("/login")
				.permitAll()
		).logout(logout -> logout
				.logoutSuccessUrl("/homepage")
		).authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/homepage","/login","/register").permitAll()
				.requestMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
		return http.build();
	}
	
	public static UserDetailsManager manager = null;
	
	@Autowired
	private UserService userService;
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> users = userService.getAllUsers().stream().map(
				account -> User
				.builder()
//				.passwordEncoder(password -> new BCryptPasswordEncoder().encode(password))
				.username(account.getUserName())
				.password("{bcrypt}"+account.getUserPassword())
				.roles("USER")
				.build()
				).toList();
		
		manager = new InMemoryUserDetailsManager(users);
		
		manager.createUser(User
				.withDefaultPasswordEncoder()
				.username("Admin")
				.password("123456")
				.roles("ADMIN")
				.build());
		return manager;
	}
	
	public static void addUser(String userName, String userPassword) {
		manager.createUser(User
				.builder()
				.username(userName)
				.password("{bcrypt}"+userPassword)
				.roles("USER")
				.build());
	}
}