package com.niit.collaborationback.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
	public List<Forum> list() {
		@SuppressWarnings("unchecked")
		List<Forum> listForum = (List<Forum>) 
		sessionFactory.getCurrentSession().createCriteria(Forum.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
	public Forum saveOrUpdate(Forum forum) {
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
          return forum;
	}
	@Transactional	
	public Forum create(Forum forum) {
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
		return forum;
	}
	
	
	@Transactional
	public List<Forum> getAcceptedList() {
		// TODO Auto-generated method stub
		String hql = "from Forum where status = " + "'A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Forum> list = (List<Forum>) query.list();
		
		return list;
	}

	@Transactional
	public List<Forum> getNotAcceptedList() {
		// TODO Auto-generated method stub
		String hql = "from Forum where status = " + "'NA'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Forum> list = (List<Forum>) query.list();
		
		return list;

	}

}
