package com.example.demo.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserOrder;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserOrderRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserOrderService {
	@Autowired
	UserOrderRepository userOrderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void insert(String unitAmount, String paymentMethod, Integer productId, Integer userId) {
		Timestamp orderTime = new Timestamp(System.currentTimeMillis());
		userOrderRepository.save(new UserOrder(orderTime, unitAmount, paymentMethod, userRepository.findByUserId(userId), productRepository.findByProductId(productId)));
	}
	
	public List<UserOrder> selectByUserId(Integer userId){
		return userOrderRepository.findByUserUserIdOrderByOrderTimeDesc(userId);
	}
	
	public List<UserOrder> selectByProductId(Integer productId) {
		return userOrderRepository.findByProductProductIdOrderByOrderTimeDesc(productId);
	}
	
	public boolean validateOrderByUserName(Integer productId, String userName) {
		UserOrder userOrder = userOrderRepository.findByProductProductIdAndUserUserName(productId, userName);
		if(userOrder == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public UserOrder selectByUserOrderId(Integer userOrderId) {
		return userOrderRepository.findByUserOrderId(userOrderId);
	}
	
	public List<UserOrder> selectByAll() {
		return userOrderRepository.findAllByOrderByOrderTimeDesc();
	}
	
	public List<Object[]> countOrder(){
		return userOrderRepository.getProductTitleAndPriceAndCountOrderByCountDesc();
	}
}
