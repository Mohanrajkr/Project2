package com.niit.collaborationback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationback.dao.ForumDAO;
import com.niit.collaborationback.model.Forum;

@Repository("ForumDAO")
public class ForumDAOImpl implements ForumDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Forum> list() {
		List<Forum> listForum = sessionFactory.getCurrentSession().createQuery("from Forum").list();
		return listForum;
	}

	@Transactional
	public Forum getByForumId(int forumId) {

		Forum ForumId = (Forum) sessionFactory.getCurrentSession().get(Forum.class, forumId);

		return ForumId;
	}

	@Transactional
	public Forum getBynName(String userName) {
		Forum Name = (Forum) sessionFactory.getCurrentSession().get(Forum.class, userName);

		return Name;
	}

	@Transactional
	public void delete(int forumId) {

		Forum forumToDelete = new Forum();
		forumToDelete.setForumId(forumId);
		sessionFactory.getCurrentSession().delete(forumToDelete);
	}

	@Transactional
	public void save(Forum forum) {
		sessionFactory.getCurrentSession().save(forum);

	}

	@Transactional
	public void update(Forum forum) {
		sessionFactory.getCurrentSession().update(forum);
	}
}
