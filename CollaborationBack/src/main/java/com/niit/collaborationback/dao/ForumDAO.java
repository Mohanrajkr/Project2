package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.Forum;

public interface ForumDAO {
	public List<Forum>list();

	public void save(Forum forum);
	public void update(Forum forum);
	public void delete (int forumId);
	public Forum getByForumId(int forumId);
	public Forum getBynName(String userName);
}
