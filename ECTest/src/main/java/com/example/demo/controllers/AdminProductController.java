package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/all")
	public String getAdminPage(Model model) {

		List<Product> productList = productService.selectByAll();

		model.addAttribute("productList",productList);

		return "productView.html";
	}

	
	@GetMapping("/publish")
	public String getProductCreatePage() {

		return "productPublish.html";
	}

	@PostMapping("/publish")
	public String publish(@RequestParam String productTitle,@RequestParam String productAbstract,@RequestParam String productDetail,@RequestParam String price,@RequestParam String category) {
		
		productService.insert(productTitle, productAbstract, productDetail, price, category);

		return "redirect:/admin/product/all";
	}

	@GetMapping("/edit/{productId}")
	public String getProductDetailPage(@PathVariable Integer productId, Model model) {

		Product product = productService.selectByProductId(productId);

		model.addAttribute("product",product);	

		return "productEdit.html";
	}
	
	@PostMapping("/update")
	public String updateData(@RequestParam Integer productId,@RequestParam String productTitle,@RequestParam String productAbstract,@RequestParam String productDetail,@RequestParam String price,@RequestParam String category) {

		productService.update(productId, productTitle, productAbstract, productDetail, price, category);

		return "redirect:/user/product/all";
	}

	@PostMapping("/delete")
	public String productDelete(@RequestParam Integer productId) {
		//Serviceクラスに値をわたし、削除処理を行う。
		productService.deleteProduct(productId);
		return "redirect:/user/product/all";
	}
	
	@GetMapping("/order")
	public String getProductOrderPage() {

		return "adminOrder.html";
	}
}


