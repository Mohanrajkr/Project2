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

import com.niit.collaborationback.dao.ForumDAO;
import com.niit.collaborationback.model.Forum;


@RestController

public class ForumController {
	@Autowired
	private ForumDAO forumDAO;
	
	public ForumDAO getForumDAO() {
		return forumDAO;
	}

	public void setForumDAO(ForumDAO forumDAO) {
		this.forumDAO = forumDAO;
	}

	@GetMapping("/forum")
	public ResponseEntity<List<Forum>> getForum() {
		List<Forum> listforum = forumDAO.list();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/forum/{forumId}")
	public ResponseEntity getByForumid(@PathVariable("forumId") int forumId) {

		Forum forum = forumDAO.getByForumId(forumId);
		if (forum == null) {
			return new ResponseEntity("No Forum found for ID " + forumId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/forum")
	public ResponseEntity createForum(@RequestBody Forum forum) {

		forumDAO.create(forum);

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/forum/{forumId}")
	public ResponseEntity deleteForum(@PathVariable int forumId) {
		Forum forum=forumDAO.getByForumId(forumId);
 		if (forum==null) {
			return new ResponseEntity("No Forum found for ID " + forumId, HttpStatus.NOT_FOUND);
		}
 		forumDAO.delete(forumId);
		return new ResponseEntity(forumId, HttpStatus.OK);
   }
	
	@SuppressWarnings("unchecked")
	@PutMapping("/forum/{forumId}")
	public ResponseEntity saveorupdateForum(@PathVariable int forumId, @RequestBody Forum forum) {

		forum = forumDAO.saveOrUpdate(forum);
		if (null == forum) {
			return new ResponseEntity("No Forum found for ID " + forumId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}

}
