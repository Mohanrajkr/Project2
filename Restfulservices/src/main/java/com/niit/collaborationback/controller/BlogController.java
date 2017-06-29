package com.niit.collaborationback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationback.dao.BlogDAO;
import com.niit.collaborationback.model.Blog;



@RestController
public class BlogController {
	@Autowired
	private BlogDAO blogDAO;
	
	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	@GetMapping("/blog")
	public ResponseEntity<List<Blog>> getBlog() {
		List<Blog> listblog = blogDAO.list();
		return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/blog/{blogId}")
	public ResponseEntity getByBlogid(@PathVariable("blogId") int blogId) {

		Blog blog = blogDAO.getById(blogId);
		if (blog == null) {
			return new ResponseEntity("No Blog found for ID " + blogId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blog, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/blog")
	public ResponseEntity createBlog(@RequestBody Blog blog) {

		blogDAO.create(blog);

		return new ResponseEntity(blog, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/blog/{blogId}")
	public ResponseEntity deleteBlog(@PathVariable int blogId) {
		Blog blog=blogDAO.getById(blogId);
 		if (blog==null) {
			return new ResponseEntity("No Blog found for ID " + blogId, HttpStatus.NOT_FOUND);
		}
 		blogDAO.delete(blogId);
		return new ResponseEntity(blogId, HttpStatus.OK);
   }
	
	@SuppressWarnings("unchecked")
	@PutMapping("/blog/{blogId}")
	public ResponseEntity saveorupdateBlog(@PathVariable int blogId, @RequestBody Blog blog) {

		blog = blogDAO.saveOrUpdate(blog);
		if (null == blog) {
			return new ResponseEntity("No Blog found for ID " + blogId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blog, HttpStatus.OK);
	}

	
}
 	