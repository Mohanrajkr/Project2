package com.niit.collaborationback.dao;

import java.util.List;

import com.niit.collaborationback.model.Friend;


public interface FriendDAO {
	public List<Friend> list();

	public List<Friend> getByUser(int userId);

	public List<Friend> getByName(String userName);

	public List<Friend> getByFriendName(String userName);

	public void save(Friend friend);

	public Friend saveOrUpdate(Friend friend);

	public List<Friend> getByFriendAccepted(String friendName);

    public List<Friend> list(int userId);

    public void delete(int friendId);

    public Friend getByFriendId(int friendId);
}
