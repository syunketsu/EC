package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="account")
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@NonNull
	@Column(name="user_name")
	private String userName;

	@NonNull
	@Column(name="user_password")
	private String userPassword;
	
    // 使用BCryptPasswordEncoder对密码进行加密
	@PrePersist
    public void prePersist() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.userPassword = passwordEncoder.encode(this.userPassword);
    }
}



