package com.example.demo.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.example.demo.models.UserOrder;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserOrderService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserOrderService userOrderService;
	
	@Autowired
	UserService userService;

	@GetMapping("/all")
	public String getProductViewPage(Model model) {

		List<Product> productList = productService.selectByAll();

		model.addAttribute("productList",productList);

		return "productView.html";
	}

	@GetMapping("/publish")
	public String getProductPublishPage() {

		return "productPublish.html";
	}

	@PostMapping("/publish")
	public String publish(@RequestParam String productTitle,@RequestParam String productAbstract,@RequestParam String productDetail,@RequestParam String price,@RequestParam String category) {
		
		productService.insert(productTitle, productAbstract, productDetail, price, category);

		return "redirect:/admin/product/all";
	}

	@GetMapping("/edit/{productId}")//是一个路径变量，用于从URL路径中获取一个名为 productId 的参数值。
	public String getProductEditPage(@PathVariable Integer productId, Model model) {

		Product product = productService.selectByProductId(productId);

		model.addAttribute("product",product);	

		return "productEdit.html";
	}
	
	@PostMapping("/update")
	public String updateData(@RequestParam Integer productId,@RequestParam String productTitle,@RequestParam String productAbstract,@RequestParam String productDetail,@RequestParam String price,@RequestParam String category) {

		productService.update(productId, productTitle, productAbstract, productDetail, price, category);

		return "redirect:/admin/product/all";
	}

//	@PostMapping("/delete")
//	public String productDelete(@RequestParam Integer productId) {
//		
//		productService.deleteProduct(productId);
//		
//		return "redirect:/admin/product/all";
//	}
	
	@GetMapping("/order")
	public String getAdminOrderPage(Model model) {
		
		List<UserOrder> userOrderList = userOrderService.selectByAll();
		
		model.addAttribute("userOrderList",userOrderList);
		
		BigDecimal totalMoney = BigDecimal.ZERO;
		for(UserOrder userOrder : userOrderList) {
			BigDecimal unitAmount = new BigDecimal(userOrder.getUnitAmount());
			totalMoney = totalMoney.add(unitAmount);
		}
		model.addAttribute("totalMoney", totalMoney);
		
	    // 获取当前时间并格式化为字符串
	    LocalDateTime currentTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTime = currentTime.format(formatter);
	    model.addAttribute("currentTime", formattedTime);
		
		return "adminOrder.html";
	}
	
	@GetMapping("/detail/{productId}")
	public String getProductDetailPage(@PathVariable Integer productId,Model model) {
		Product product = productService.selectByProductId(productId);
		model.addAttribute("product",product);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		model.addAttribute("name",userName);
		
		if(userOrderService.validateOrderByUserName(productId,userName)) {
			model.addAttribute("already",false);
		}else {
			model.addAttribute("already",true);
		}	
		
		return "productDetail.html";
	}
}


