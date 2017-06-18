package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.Friend;


public interface FriendDAO {
	public List<Friend>list();

	public Friend saveOrUpdate(Friend friend);
	public Friend create(Friend friend);

	public void delete (int friendId);
	public Friend getByFid(int friendId);
	public Friend getByStatus(String status);
}
