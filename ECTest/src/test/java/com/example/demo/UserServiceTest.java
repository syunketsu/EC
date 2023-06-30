package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;


@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void prepareData() {
//    	 PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        when(userRepository.findByUserName(eq("aaa"))).thenReturn(new User("aaa", "aaa"));//找到aaa这个人，返回他的信息
    }

//    @Test
//    public void testValidateUser_ValidUser_ReturnsTrue() {
//        boolean user = userService.validateUser("aaa", "aaa");
//        assertTrue(user);
//    }


    @Test
    public void testCreateUser_NewUser_ReturnsTrue() {
        boolean newUser = userService.createUser("newuser", "password");
        assertTrue(newUser);
    }
}
