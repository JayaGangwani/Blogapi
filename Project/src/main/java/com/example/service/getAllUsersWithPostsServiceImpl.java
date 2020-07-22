package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.getAllUsersWithPostsDao;
import com.example.model.User;

@Service
public class getAllUsersWithPostsServiceImpl implements getAllUsersWithPostsService {

	@Autowired
	private getAllUsersWithPostsDao allUsersWithPostsDao;

	@Override
	public List<User> getAllUsersWithPosts() throws Exception {

		List<User> usersWithPosts = allUsersWithPostsDao.getAllUsersWithPosts();

		if (usersWithPosts==null || usersWithPosts.isEmpty()) {
			throw new Exception("Service.UserNotFound");
		}
		return usersWithPosts;
	}

}
