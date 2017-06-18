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

import com.niit.collaborationback.dao.FriendDAO;
import com.niit.collaborationback.model.Friend;

@RestController

public class FriendController {
	@Autowired
	private FriendDAO friendDAO;
	
	public FriendDAO getFriendDAO() {
		return friendDAO;
	}

	public void setFriendDAO(FriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}

	@GetMapping("/friend")
	public ResponseEntity<List<Friend>> getFriend() {
		List<Friend> listfriend = friendDAO.list();
		return new ResponseEntity<List<Friend>>(listfriend, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/friend/{friendId}")
	public ResponseEntity getByFriendid(@PathVariable("friendId") int friendId) {

		Friend friend = friendDAO.getByFid(friendId);
		if (friend == null) {
			return new ResponseEntity("No Friend found for ID " + friendId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(friend, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/friend")
	public ResponseEntity createFriend(@RequestBody Friend friend) {

		friendDAO.create(friend);

		return new ResponseEntity(friend, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/friend/{friendId}")
	public ResponseEntity deleteFriend(@PathVariable int friendId) {
		Friend friend=friendDAO.getByFid(friendId);
 		if (friend==null) {
			return new ResponseEntity("No Friend found for ID " + friendId, HttpStatus.NOT_FOUND);
		}
 		friendDAO.delete(friendId);
		return new ResponseEntity(friendId, HttpStatus.OK);
   }
	
	@SuppressWarnings("unchecked")
	@PutMapping("/friend/{friendId}")
	public ResponseEntity saveorupdateUser(@PathVariable int friendId, @RequestBody Friend friend) {

		friend = friendDAO.saveOrUpdate(friend);
		if (null == friend) {
			return new ResponseEntity("No Friend found for ID " + friendId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(friend, HttpStatus.OK);
	}

}
