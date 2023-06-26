package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	User save(User User);
	
	User findByUserName(String userName);//根据用户名查找并返回一个完整的User实体对象
	
	List<User> findAll();
	
	User findByUserId(Integer userId);
}

