package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.Chat;


public interface ChatDAO {
	
		public List<Chat>list();
		public Chat saveOrUpdate(Chat chat);
		public Chat create(Chat chat);

		public void delete (int chatId);
		
		public Chat getByCid(int chatId);
}
