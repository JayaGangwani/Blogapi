package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserInformationPostsService {
	public List<User> getAllUsersWithPosts() throws Exception;
}
