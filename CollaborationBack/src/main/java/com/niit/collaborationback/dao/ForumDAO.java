package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.Forum;


public interface ForumDAO {
	public List<Forum>list();

	public Forum saveOrUpdate(Forum forum);
	public void create(Forum forum);

	public void delete (int forumId);
	public Forum getByForumId(int forumId);
	public Forum getBynName(String userName);
	public List<Forum> getAcceptedList();
	
	public List<Forum> getNotAcceptedList();
}
