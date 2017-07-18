package com.niit.collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationback.dao.ForumDAO;
import com.niit.collaborationback.model.Forum;



public class ForumDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static ForumDAO forumDAO;

	@Autowired
	static Forum forum;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaborationback");
		context.refresh();

		// get the userDAO from context
		forumDAO = (ForumDAO) context.getBean("ForumDAO");

		forum = (Forum) context.getBean("forum");
	}

	@Test
	public void createForumTestCase() {

		forum.setForumName("Discussion board");
	  	forum.setForumContent("school system will change");
	  	forum.setStatus("A");
	  	forumDAO.saveOrUpdate(forum);

	}

}
