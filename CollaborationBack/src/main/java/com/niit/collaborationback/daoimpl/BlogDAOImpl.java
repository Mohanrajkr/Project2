package com.niit.collaborationback.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationback.dao.BlogDAO;
import com.niit.collaborationback.model.Blog;

@Repository("BlogDAO")
public class BlogDAOImpl implements BlogDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public List<Blog> list() {
		@SuppressWarnings("unchecked")
		List<Blog> listBlog = sessionFactory.getCurrentSession().createQuery("from Blog").list();
		return listBlog;
	}
	@Transactional
	public void save(Blog blog) {
		sessionFactory.getCurrentSession().save(blog);
		
	}

	@Transactional
	public void delete(int blogId) {
		Blog blogToDelete = new Blog();
		blogToDelete.setBlogId(blogId);
		sessionFactory.getCurrentSession().delete(blogToDelete);
	}
	@Transactional
	public Blog getById(int blogId) {
		Blog BlogId = (Blog) sessionFactory.getCurrentSession().get(Blog.class, blogId);

		return BlogId;
	}
	@Transactional
	public Blog getByTitle(String title) {
		Blog Title = (Blog) sessionFactory.getCurrentSession().get(Blog.class,title);

		return Title;
	}
	@Transactional
	public void update(Blog blog) {
		sessionFactory.getCurrentSession().update(blog);
		
	} 
	  @Transactional
	    public void insert(Blog blog) {
	        sessionFactory.getCurrentSession().saveOrUpdate(blog);
	    }
	    @Transactional
	    public List<Blog> getAllBlog(int blogId) {
	        System.out.println("Retrieve Comment"+blogId);
	        Session session=(Session) sessionFactory.openSession();
	        Query query=(Query) session.createQuery("from Blog where blogId=:blogId ");
	        query.setParameter("blogId", new Integer(blogId));
	        @SuppressWarnings("unchecked")
	        List<Blog> blog=((BlogDAO) query).list();
	        session.close();
	        return blog;
	    }
}
