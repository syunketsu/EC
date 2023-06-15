package com.example.demo.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	//内容を保存
	public void insert(String productTitle, String productAbstract, String productDetail, String price, String category) {
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		productRepository.save(new Product(productTitle, productAbstract, productDetail, price, category, createTime));
	}
	
	//プロダクト一覧
	public List<Product> selectByAll(){
		return productRepository.findAllByOrderByCreateTimeDesc();
	}

	//あるプロダクト詳細
	public Product selectByProductId(Integer productId){
		return productRepository.findByProductId(productId);
	}
	
	//内容を更新
	public void update(Integer productId, String productTitle, String productAbstract, String productDetail, String price, String category) {
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		productRepository.save(new Product(productTitle, productAbstract, productDetail, price, category, createTime));
	}
	
	//削除
	public List<Product> deleteProduct(Integer productId){
		return productRepository.deleteByProductId(productId);
	}
}
