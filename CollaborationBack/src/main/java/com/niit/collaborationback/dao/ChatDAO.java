package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.Chat;

public interface ChatDAO {
	
		public List<Chat>list();
		public void save(Chat chat);
		public void update(Chat chat);
		public void delete (int chatId);
		
		public Chat getByCid(int chatId);
}
