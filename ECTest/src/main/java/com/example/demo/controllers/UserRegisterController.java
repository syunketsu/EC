package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.User;
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
	
	@GetMapping("/infoEdit")
	public String getInfoUpdatePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String userName = auth.getName();
		
		User user = userService.selectById(userName);
		
//		String userpassword = user.getUserPassword();
//		
//		Integer userid = user.getUserId();
		
		model.addAttribute("user",user);
		
		return "userInfoEdit.html";
	}
	
	@PostMapping("/infoUpdate")
	public String updateData(@RequestParam Integer userId, @RequestParam String userName, @RequestParam String userPassword) {
		userService.update(userId, userName, userPassword);
		
		return "redirect:/userhomepage";
	}
	
}
