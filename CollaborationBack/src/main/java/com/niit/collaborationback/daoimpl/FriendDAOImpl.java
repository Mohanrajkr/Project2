package com.niit.collaborationback.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
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

	@Transactional
	public List<Friend> list() {
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) 
		sessionFactory.getCurrentSession().createCriteria(Friend.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listFriend;
	}

	@Transactional	
	public Friend create(Friend friend) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
		return friend;
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
public Friend saveOrUpdate(Friend friend) {
	sessionFactory.getCurrentSession().saveOrUpdate(friend);
	return friend;
}

}
