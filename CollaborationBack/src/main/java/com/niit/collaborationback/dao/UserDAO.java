package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.User;

public interface UserDAO {

	public List<User> list();

	public void save(User user);

	public void update(User user);

	public void delete(int userId);

	public User getByUserId(int userId);

	public User getByMail(String mail);

	

	public User login(User user);
	
	
}
