package com.niit.collaborationback.controller;

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

import com.niit.collaborationback.dao.FriendDAO;
import com.niit.collaborationback.model.Friend;
import com.niit.collaborationback.model.User;

@RestController

public class FriendController {
	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private Friend friend;
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
	@GetMapping("/friends/{friendId}")
	public ResponseEntity getByFriendid(@PathVariable("friendId") int friendId) {

		Friend friend = friendDAO.getByFriendId(friendId);
		if (friend == null) {
			return new ResponseEntity("No Friend found for ID " + friendId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(friend, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/friend/{userId}")
	public List<Friend> getByUser(@PathVariable("userId")int userId) {
		System.out.println(userId);
		return friendDAO.getByUser(userId);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/friend")
	public ResponseEntity createFriend(@RequestBody User friendUser,HttpSession session) {

		User user = (User) session.getAttribute("user");   
		friend.setUserId(user.getUserId());
		friend.setUserName(user.getUserName());
		friend.setStatus("R");
		friend.setFriendId(friendUser.getUserId());
		friend.setFriendName(friendUser.getUserName());
		friend.setIsOnline("TRUE");
	
		friendDAO.save(friend);

		return new ResponseEntity(friend, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/friend/{friendId}")
	public ResponseEntity deleteFriend(@PathVariable int friendId) {
		Friend friend=friendDAO.getByFriendId(friendId);
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
