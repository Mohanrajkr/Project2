package com.niit.collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationback.dao.ChatDAO;
import com.niit.collaborationback.model.Chat;


public class ChatDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static ChatDAO chatDAO;

	@Autowired
	static Chat chat;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaborationback");
		context.refresh();

		// get the userDAO from context
		chatDAO = (ChatDAO) context.getBean("ChatDAO");

		chat = (Chat) context.getBean("chat");
	}

	@Test
	public void createChatTestCase() {

		chat.setMessage("hello");
		chat.setSender("ravi");
		chat.setReceiver("raja");
		
		chatDAO.saveOrUpdate(chat);

	}
}
