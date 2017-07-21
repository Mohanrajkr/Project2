package com.niit.collaborationback.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationback.dao.AppliedJobsDAO;
import com.niit.collaborationback.dao.BlogDAO;
import com.niit.collaborationback.dao.ChatDAO;
import com.niit.collaborationback.dao.ForumDAO;
import com.niit.collaborationback.dao.FriendDAO;
import com.niit.collaborationback.dao.JobDAO;
import com.niit.collaborationback.dao.UserDAO;
import com.niit.collaborationback.daoimpl.AppliedJobsDAOImpl;
import com.niit.collaborationback.daoimpl.BlogDAOImpl;
import com.niit.collaborationback.daoimpl.ChatDAOImpl;
import com.niit.collaborationback.daoimpl.ForumDAOImpl;
import com.niit.collaborationback.daoimpl.FriendDAOImpl;
import com.niit.collaborationback.daoimpl.JobDAOImpl;
import com.niit.collaborationback.daoimpl.UserDAOImpl;
import com.niit.collaborationback.model.AppliedJobs;
import com.niit.collaborationback.model.Blog;
import com.niit.collaborationback.model.Chat;
import com.niit.collaborationback.model.Forum;
import com.niit.collaborationback.model.Friend;
import com.niit.collaborationback.model.Job;
import com.niit.collaborationback.model.User;

@Configuration
@ComponentScan("com.niit.*")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		dataSource.setUsername("COLLAB");
		dataSource.setPassword("Mohan1995");

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");

		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(AppliedJobs.class);


		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Autowired(required = true)
	@Bean(name = "UserDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "ForumDAO")
	public ForumDAO getForumDAO(SessionFactory sessionFactory) {
		return new ForumDAOImpl(sessionFactory);
	}
	
	
	@Autowired(required = true)
	@Bean(name = "JobDAO")
	public JobDAO getJobDAO(SessionFactory sessionFactory) {
		return new JobDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "ChatDAO")
	public ChatDAO getChatDAO(SessionFactory sessionFactory) {
		return new ChatDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "FriendDAO")
	public FriendDAO getFriendDAO(SessionFactory sessionFactory) {
		return new FriendDAOImpl(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "AppliedJobsDAO")
	public AppliedJobsDAO getAppliedJobsDAO(SessionFactory sessionFactory) {
		return new AppliedJobsDAOImpl(sessionFactory);
	}
	
}
