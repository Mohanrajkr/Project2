package com.niit.collaborationback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationback.dao.ChatDAO;
import com.niit.collaborationback.model.Chat;

@Repository("ChatDAO")
public class ChatDAOImpl implements ChatDAO{
	@Autowired
	private SessionFactory sessionFactory;
  
	public ChatDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}  
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Chat> list() {
		List<Chat> listChat = sessionFactory.getCurrentSession().createQuery("from Chat").list();
		return listChat;
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
	public void save(Chat chat) {
		sessionFactory.getCurrentSession().save(chat);
		
	}
	@Transactional
	public void update(Chat chat) {
		sessionFactory.getCurrentSession().update(chat);
		
	}
}
