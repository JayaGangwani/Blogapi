package com.example.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserInformationDao;
import com.example.exception.UserNotFoundException;
import com.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInformationServiceImpl implements UserInformationService {

	@Autowired
	private UserInformationDao allUsersWithPostsDao;

	@Override
	public List<User> getAllUsersWithPosts() throws Exception  {
        
		log.info("Required all the information of User and their posts");
		List<User> usersWithPosts = allUsersWithPostsDao.getAllUsersWithPosts();
		if (null==usersWithPosts || usersWithPosts.isEmpty()) {
			 log.error("The information of users is {}" ,usersWithPosts);
			throw new UserNotFoundException("Service.UserNotFound");
		}
		return usersWithPosts;
	}

}
