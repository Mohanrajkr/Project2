package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.Blog;

public interface BlogDAO {
	public List<Blog>list();

	public void save(Blog blog);
	public void update(Blog blog);
	public void delete (int blogId);
	public Blog getById(int blogId);
	public Blog getByTitle(String title);
}
