package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

@Controller
public class UserLoginController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}
	
	@GetMapping("/homepage")
	public String getHomePage(Model model) {		
		List<Product> productList = productService.selectByAll();
		model.addAttribute("productList",productList);
		return "homepage.html";
	}
	
	@GetMapping("/userhomepage")
	public ModelAndView getUserHomePage(Principal principal) {
		ModelAndView mav = new ModelAndView("homepage.html");
		String userName = principal.getName();
		mav.addObject("name", userName);
		
		List<Product> productList = productService.selectByAll();
		mav.addObject("productList",productList);
		return mav;
	}
	
	@GetMapping("/loginerror")
	public ModelAndView getUserErrorPage() {
		ModelAndView mav = new ModelAndView("login.html");
		mav.addObject("error", true);
		return mav;
	}
	
	@GetMapping("/page")
	public String getPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
	        return "redirect:/admin/product/all"; // ADMIN角色跳转到/admin/product/all路径
	    } else {
	        return "redirect:/userhomepage"; // USER角色跳转到/userhomepage路径
	    } 
	}
}


