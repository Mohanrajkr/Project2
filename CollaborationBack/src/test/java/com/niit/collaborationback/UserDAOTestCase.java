package com.niit.collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationback.dao.UserDAO;
import com.niit.collaborationback.model.User;

public class UserDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static UserDAO userDAO;

	@Autowired
	static User user;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaborationback");
		context.refresh();

		// get the userDAO from context
		userDAO = (UserDAO) context.getBean("UserDAO");

		user = (User) context.getBean("user");
	}

	@Test
	public void createUserTestCase() {

		user.setUserName("Ram");
		user.setPassword("45");
		user.setMobileNumber("8807761502");
		user.setEmail("ram@gmail.com");
		user.setAddress("Tirupur");
		user.setRole("hmm");
		userDAO.saveOrUpdate(user);

	}

}