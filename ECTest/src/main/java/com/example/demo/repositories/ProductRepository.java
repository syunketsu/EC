package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> { 
	//保存
	Product save(Product product);

	//productIdを使用してDBに検索をかける
	Product findByProductId(Integer productId);
	
	//プロダクトテーブルのすべての情報を取得
	List<Product> findAllByOrderByCreateTimeDesc();

	//productIdを取得して該当するブログ情報を削除する
	@Transactional
	List<Product> deleteByProductId(Integer productId);
}