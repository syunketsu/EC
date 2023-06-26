package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.models.UserOrder;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserOrderService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("user/product")
public class UserProductController {
	@Autowired
	UserOrderService userOrderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/pay/{productId}")
	public String getUserPayPage(@PathVariable Integer productId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = userService.selectByUserName(userName);
		Integer userid = user.getUserId();
		model.addAttribute("userId",userid);
		
		Product product = productService.selectByProductId(productId);

		model.addAttribute("product",product);	
		
		return "userPay.html";
	}
	
	@PostMapping("/pay")
	public String pay(@RequestParam String unitAmount,@RequestParam String paymentMethod,@RequestParam Integer productId,@RequestParam Integer userId) {
		userOrderService.insert(unitAmount, paymentMethod, productId, userId);
		
		return "redirect:/user/product/order";
	}
	
	@GetMapping("/order")
	public String getUserOrderPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		model.addAttribute("name",userName);
		
		User user = userService.selectByUserName(userName);
		
		Integer userid = user.getUserId();
		List<UserOrder> userOrderList = userOrderService.selectByUserId(userid);
		model.addAttribute("userOrderList",userOrderList);
		
		return "userOrder.html";
	}
	
	@GetMapping("/all")
	public String getUserLessonsPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		model.addAttribute("name",userName);
		
		User user = userService.selectByUserName(userName);
		
		Integer userid = user.getUserId();
		List<UserOrder> userOrderList = userOrderService.selectByUserId(userid);
		model.addAttribute("userOrderList",userOrderList);
		
		return "userLessons.html";
	}
}
