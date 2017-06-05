package com.niit.collaborationback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
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
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Friend> list() {
		List<Friend> listFriend = sessionFactory.getCurrentSession().createQuery("from Friend").list();
		return listFriend;
	}

	
@Transactional
	public void delete(int friendId) {
	Friend friendToDelete = new Friend();
	friendToDelete.setFriendId(friendId);
	sessionFactory.getCurrentSession().delete(friendToDelete);

	}
@Transactional
	public Friend getByFid(int friendId) {
	Friend Fid = (Friend) sessionFactory.getCurrentSession().get(Friend.class, friendId);

	return Fid;
	}
@Transactional
public Friend getByStatus(String status) {
	Friend Status = (Friend) sessionFactory.getCurrentSession().get(Friend.class, status);

	return Status;

}
@Transactional
public void save(Friend friend) {
	sessionFactory.getCurrentSession().save(friend);
	
}
@Transactional
public void update(Friend friend) {
	
	sessionFactory.getCurrentSession().update(friend);
}
}
