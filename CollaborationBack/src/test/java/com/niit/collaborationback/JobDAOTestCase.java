package com.niit.collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationback.dao.JobDAO;
import com.niit.collaborationback.model.Job;


public class JobDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static JobDAO jobDAO;

	@Autowired
	static Job job;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaborationback");
		context.refresh();


		jobDAO = (JobDAO) context.getBean("JobDAO");

		job = (Job) context.getBean("job");
	}

	@Test
	public void createJobTestCase() {

		job.setTitle("software engineer");
		job.setCompanyName("codedrop");
		job.setQualification("B.Tech");
		job.setEmail("moh@gmail.com");
		job.setStatus("required");
	   	jobDAO.saveOrUpdate(job);

	}

}
