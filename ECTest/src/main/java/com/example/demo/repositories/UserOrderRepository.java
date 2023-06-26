package com.example.demo.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserOrder;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer>{
	UserOrder save(UserOrder userOrder);
	
	List<UserOrder> findByUserUserIdOrderByOrderTimeDesc(Integer userId);
	
	List<UserOrder> findByProductProductIdOrderByOrderTimeDesc(Integer productId);
	
	UserOrder findByProductProductIdAndUserUserName(Integer productId, String userName);
	
//	UserOrder findByProductProductIdAndUserUserId(Integer productId, Integer userId);
	
	UserOrder findByUserOrderId(Integer userOrderId);
	
	List<UserOrder> findAllByOrderByOrderTimeDesc();
	
	@Query("SELECT ROW_NUMBER() OVER (ORDER BY COUNT(uo) DESC) AS rowNumber, "
			+ "uo.product.productTitle AS productTitle, "
			+ "uo.product.price AS price, "
			+ "COUNT(uo) AS count FROM UserOrder uo GROUP BY uo.product.productTitle, uo.product.price ORDER BY COUNT(uo) DESC")
	List<Object[]> getProductTitleAndPriceAndCountOrderByCountDesc();
}
