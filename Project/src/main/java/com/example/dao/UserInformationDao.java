package com.example.dao;

import java.util.List;

import com.example.model.User;

public interface UserInformationDao {
	public List<User> getAllUsersWithPosts() throws Exception;
}
