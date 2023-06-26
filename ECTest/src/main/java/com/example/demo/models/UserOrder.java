package com.example.demo.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "userOrder")
public class UserOrder {
	@Id
	@Column(name="user_order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userOrderId;
	
	@NonNull
	@Column(name = "order_time")
	private Timestamp orderTime;
	
	@NonNull
	@Column(name = "unit_amount")
	private String unitAmount;
	
	@NonNull
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "user_id_key",referencedColumnName = "user_id")
	private User user;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "product_id_key",referencedColumnName = "product_id")
	private Product product;
}
