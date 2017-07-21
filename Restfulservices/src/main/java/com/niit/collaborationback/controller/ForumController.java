package com.niit.collaborationback.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.niit.collaborationback.model.User;


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
	
	@GetMapping("/acceptedForum")
	public ResponseEntity<List<Forum>> acceptedForumsList() {
		List<Forum> listforum = forumDAO.getAcceptedList();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	@GetMapping("/notAcceptedforum")
	public ResponseEntity<List<Forum>> notAcceptedForumList() {
		List<Forum> listforum = forumDAO.getNotAcceptedList();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/acceptForum")
	public ResponseEntity acceptForum(@RequestBody Forum forum){
		forum.setStatus("A");
		 forumDAO.saveOrUpdate(forum);
		return new ResponseEntity(forum, HttpStatus.OK);
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
	public ResponseEntity createForum(@RequestBody Forum forum,HttpSession session) {

		forum.setCreateDate(new Date());
		forum.setStatus("NA");
		
		User user = (User) session.getAttribute("user");   
		System.out.println(forum.getForumName());
		forum.setUserId(user.getUserId());
		forum.setUserName(user.getUserName());
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity(forum,HttpStatus.OK);
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
