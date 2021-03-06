package com.niit.collaborationback.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationback.dao.UserDAO;
import com.niit.collaborationback.model.User;
@RestController
public class UserController {
	@Autowired
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> getUsers() {
		List<User> listuser = userDAO.list();
		return new ResponseEntity<List<User>>(listuser, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/user/{userId}")
	public ResponseEntity getByUserid(@PathVariable("userId") int userId) {

		User user = userDAO.getByUserId(userId);
		if (user == null) {
			return new ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/user")
	public ResponseEntity createUser(@RequestBody User user) {

		userDAO.create(user);

		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/user/{userId}")
	public ResponseEntity deleteUser(@PathVariable int userId) {
		User user=userDAO.getByUserId(userId);
 		if (user==null) {
			return new ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND);
		}
 		userDAO.delete(userId);
		return new ResponseEntity(userId, HttpStatus.OK);
   }
	
	@SuppressWarnings("unchecked")
	@PutMapping("/user/{userId}")
	public ResponseEntity saveorupdateUser(@PathVariable int userId, @RequestBody User user) {

		user = userDAO.saveOrUpdate(user);
		if (null == user) {
			return new ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}

	
}
