package com.example.demo.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void prepareData() {
        when(userService.createUser(notNull(), notNull())).thenReturn(true); // 创建账户，有用户名和密码
        when(userService.createUser(eq("aaa"), eq("aaa"))).thenReturn(false); // 核对账户，数据库已经有了该账户
    }

    // 页面符合预期
    @Test
    public void testGetRegisterPage_Succeed() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/register");

        mockMvc.perform(request)
                .andExpect(view().name("register.html"))
                .andExpect(model().attributeDoesNotExist("error"));
    }

    // 成功注册账户 跳转登录页面
    @Test
    public void testRegister_SuccessfulRegistration_RedirectToLoginPage() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/register")
                .with(csrf())
                .param("userName", "bbb")
                .param("userPassword", "bbb");

        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login.html"));

    }

    @Test
    public void testRegister_ExistingUsername_ReturnError() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/register")
                .with(csrf())
                .param("userName", "aaa")
                .param("userPassword", "aaa");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("register.html"))
                .andExpect(model().attribute("error", true));
    }
}
