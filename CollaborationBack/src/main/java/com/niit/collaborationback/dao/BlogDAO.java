package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.Blog;


public interface BlogDAO {
	public List<Blog>list();

	public Blog saveOrUpdate(Blog Blog);
	public Blog create(Blog Blog);

	public void delete (int blogId);
	public Blog getById(int blogId);
	public Blog getByTitle(String title);
	 public List<Blog> getAcceptedList();
		
		public List<Blog> getNotAcceptedList();
}
