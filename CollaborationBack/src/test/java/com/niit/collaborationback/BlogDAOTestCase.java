package com.niit.collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationback.dao.BlogDAO;
import com.niit.collaborationback.model.Blog;



public class BlogDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static BlogDAO blogDAO;

	@Autowired
	static Blog blog;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaborationback");
		context.refresh();

		
		blogDAO = (BlogDAO) context.getBean("BlogDAO");

		blog = (Blog) context.getBean("blog");
	}

	@Test
	public void createBlogTestCase() {

		blog.setTitle("placement session");
		blog.setDescription("www.indiabixapp.com,www.mathcrack.com");
	    blog.setStatus("active");
	    blogDAO.saveOrUpdate(blog);

	}

}
