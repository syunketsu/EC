package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Product;
import com.example.demo.services.UserService;

@Controller
public class UserRegisterController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}
	
	@PostMapping("/register")
	public ModelAndView register(@RequestParam String userName,
			@RequestParam String userPassword, ModelAndView mav) {
		if (userService.createUser(userName, userPassword)) {
			mav.addObject("error", false);
			mav.setViewName("login.html");
		} else {
			mav.addObject("error", true);
			mav.setViewName("register.html");
		}
		return mav;
	}
	
	@GetMapping("/infoUpdate")
	public String getInfoUpdatePage() {
		return "userInfoEdit.html";
	}
	
	@PostMapping("/infoUpdate")
	public ModelAndView getInfoUpdatePage(Principal principal) {
		ModelAndView mav = new ModelAndView("userInfoEdit.html");
		String userPassword = principal.getName();
		mav.addObject("password", userPassword);
		
		return mav;
	}
}
