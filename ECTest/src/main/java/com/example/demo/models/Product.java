package com.example.demo.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product {
	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;

	@NonNull
	@Column(name="product_title")
	private String productTitle;
	
	@NonNull
	@Column(name="product_abstract")
	private String productAbstract;
	
	@NonNull
	@Column(name="product_detail")
	private String productDetail;
	
	@NonNull
	@Column(name="price")
	private String price;
	
	@NonNull
	@Column(name="category")
	private String category;
	
	@NonNull
	@Column(name="create_time")
    private Timestamp createTime;
	

	private Integer likeCount;
	

	private Integer orderCount;
}
