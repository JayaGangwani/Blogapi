package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserInformationPostsDao;
import com.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInformationPostsServiceImpl implements UserInformationPostsService {

	@Autowired
	private UserInformationPostsDao allUsersWithPostsDao;

	@Override
	public List<User> getAllUsersWithPosts() throws Exception  {
        
		log.info("Required all the information of User and their posts");
		List<User> usersWithPosts = allUsersWithPostsDao.getAllUsersWithPosts();
		if (usersWithPosts==null || usersWithPosts.isEmpty()) {
			 log.error("The information of users is" +usersWithPosts );
			throw new NullPointerException("Service.UserNotFound");
		}
		return usersWithPosts;
	}

}
