package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	User save(User User);
	
	User findByUserName(String userName);
	
	List<User> findAll();
	
	@Transactional
	List<User> deleteByUserName(String userName);
}

