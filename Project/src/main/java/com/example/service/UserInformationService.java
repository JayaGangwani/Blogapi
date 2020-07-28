package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserInformationService {
	public List<User> getAllUsersWithPosts() throws Exception;
}
