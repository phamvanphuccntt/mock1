package com.itsol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.dao.UserDAO;
import com.itsol.model.User;

@Service
public class UserService {
	@Autowired
	UserDAO userDao;
	
	public List<User> getListUser(){
		return userDao.getListUser();
	}
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}
	public boolean updateUser(User user, int userId) {
		return userDao.updateUser(userId, user);
	}
	public boolean deleteUser(int userId) {
		return userDao.deleteUser(userId);
	}
	public User getUserId(int id){
		return userDao.getUserId(id);
	}
}
