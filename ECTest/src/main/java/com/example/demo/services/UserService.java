package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import com.example.demo.WebSecurityConfig;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service //告诉spring这是一个服务层的类
public class UserService {
	@Autowired //让spring自动实现该接口并创建实例
	private UserRepository userRepository;
	
//	public boolean validateUser(String userName, String userPassword) { 
//		User user = userRepository.findByUserName(userName);//根据用户名查找并返回一个完整的User实体对象,并且将这个结果赋值给user
//		if (user == null || !user.getUserPassword().equals(userPassword)) {
//			return false;
//		} else {
//			return true;
//		}
//	} //接收两个参数userName和userPassword，判断对应的数据是否已经在数据库中
	
    public boolean validateUser(String userName, String userPassword) { 
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return false;
        }

        // 使用BCryptPasswordEncoder验证密码
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(userPassword, user.getUserPassword());
    }
	
	public boolean createUser(String userName, String userPassword) {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			userRepository.save(new User(userName, userPassword));
			WebSecurityConfig.addUser(userName, userPassword);
			return true;
		} else {
			return false;
		}
	} //接收两个参数userName和userPassword，并尝试插入一个新的用户信息，如果用户名已在数据库内，则插入失败，返回false，否则返回true
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	} //ユーザの一覧を取得する
	
	public User selectByUserName(String userName) {
		return userRepository.findByUserName(userName);
	} //根据用户名查找并返回一个完整的User实体对象
	
	public void update(Integer userId, String userName, String userPassword) {
		userRepository.save(new User(userId, userName, userPassword));
	} //更新用户信息



}
