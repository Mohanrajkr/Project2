package com.niit.collaborationback.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationback.dao.ChatDAO;
import com.niit.collaborationback.model.Chat;
import com.niit.collaborationback.model.User;

@Repository("ChatDAO")
public class ChatDAOImpl implements ChatDAO{
	@Autowired
	private SessionFactory sessionFactory;
  
	public ChatDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}  
	
	@Transactional
	public List<Chat> list() {
		@SuppressWarnings("unchecked")
		List<Chat> listChat = (List<Chat>) 
		sessionFactory.getCurrentSession().createCriteria(Chat.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listChat;
	}
	
	
	@Transactional	
	public Chat create(Chat chat) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(chat);
		return chat;
	}

	@Transactional
	public void delete(int chatId) {
		Chat eventToDelete = new Chat();
		eventToDelete.setChatId(chatId);
		sessionFactory.getCurrentSession().delete(eventToDelete);

	}
	
	
	
	@Transactional
	public Chat getByCid(int chatId) {
		Chat Cid = (Chat) sessionFactory.getCurrentSession().get(Chat.class, chatId);
		return Cid;
	}
	@Transactional
	public Chat saveOrUpdate(Chat chat) {
		sessionFactory.getCurrentSession().saveOrUpdate(chat);
		return chat;
	}
	
}
