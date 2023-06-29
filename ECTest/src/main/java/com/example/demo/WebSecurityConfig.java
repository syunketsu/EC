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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.services.UserService;

@Configuration
@EnableWebSecurity //表示启用Web安全性
public class WebSecurityConfig{
	@Bean//用于告诉Spring容器将一个方法的返回值注册为一个bean
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
	
	//manager是一个静态字段，用于保存用户的详细信息管理器。
	public static UserDetailsManager manager = null;
	
	@Autowired
	private UserService userService;
	
	//用于加载用户的详细信息
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> users = userService.getAllUsers().stream().map(
				account -> User
				.builder()//创建用户对象
				.username(account.getUserName())
				.password("{bcrypt}"+account.getUserPassword())//告诉spring这个密码是加密过的
				.roles("USER")
				.build()
				).toList();
		
		//创建一个用户详细信息管理器，并将用户添加到管理器中
		manager = new InMemoryUserDetailsManager(users);
		
		manager.createUser(User
				.builder()
				.username("Admin")
				.password("{bcrypt}$2a$10$DS6GbonNEBLr2hWDwxoaUu4sc9xnh4KGIYNtsARWCUEPogkbyoYXC")
				.roles("ADMIN")
				.build());
		return manager;
	}
	
	//用于向用户详细信息管理器中添加新用户
	public static void addUser(String userName, String userPassword) {
		manager.createUser(User
				.builder()
				.username(userName)
				.password("{bcrypt}"+userPassword)//告诉spring这个密码是加密过的
				.roles("USER")
				.build());
	}
}