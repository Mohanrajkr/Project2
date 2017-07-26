package com.niit.collaborationback.daoimpl;

import java.util.List;


import org.hibernate.SessionFactory;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationback.dao.FriendDAO;
import com.niit.collaborationback.model.Friend;


@Repository("FriendDAO")
public class FriendDAOImpl implements FriendDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Friend> list() {
	
		@SuppressWarnings("unchecked")
		List<Friend> friendList = sessionFactory.getCurrentSession().createQuery("from Friend").list();
		return friendList;
	}
	
	
@Transactional
	public List<Friend> getByUser(int userId) {
		Session session=sessionFactory.openSession();
		
		String hql = "from Friend where userId ='" + userId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> friens=query.list();
		session.close();
		return friens;
	}
@Transactional
public List<Friend> getByName(String userName) {
	Session session=sessionFactory.openSession();
	
	String hql = "from Friend where userName ='" + userName + "'";
	org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> frien=query.list();
	session.close();
	return frien;
}
@Transactional
public List<Friend> getByFriendName(String userName) {
	Session session=sessionFactory.openSession();
	String hql = "from Friend where userName =" + "'" + userName + "' and status = " + "'A'";
	org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> listFriend = (List<Friend>) query.list();
	session.close();
	return listFriend;
}


@Transactional
	public void save(Friend friend) {
		
		sessionFactory.getCurrentSession().save(friend);
	}
@Transactional
public List<Friend> getByFriendAccepted(String friendname){
	String hql = "from Friend where friendname =" + "'" + friendname + "' and status = " + "'A'";
	org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> listFriend = (List<Friend>) query.list();
	return listFriend;
}


	@Transactional
	public Friend saveOrUpdate(Friend friend) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
		return friend;
	}
	
	@Transactional
	public List<Friend> list(int userId) {
		String hql = "from Friend where userId =" + "'" + userId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();

		return listFriend;
	}
	@Transactional
	public void delete(int friendId) {
		Friend frnd = new Friend();
		frnd.setFriendId(friendId);
		sessionFactory.getCurrentSession().delete(frnd);
		}

	@Transactional
	public Friend getByFriendId(int friendId) {
	
		Friend friendListByID = (Friend) sessionFactory.getCurrentSession().get(Friend.class, friendId);

		return friendListByID;

	}
}
