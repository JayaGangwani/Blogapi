package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AdminCreatePostDao;
import com.example.model.Posts;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminCreatePostServiceImpl implements AdminCreatePostService{

	@Autowired
	private AdminCreatePostDao adminCreatePostDao;
	
	@Override
	public String createPost(Long userId, Posts post) throws Exception {
		log.info("Create Post by Admin");
		String value=adminCreatePostDao.createPost(userId,post);
		if(value==null) {
			log.error(value + " is returned after creating post");
			throw new NullPointerException("Service.createPostError");
		}
		return "Created Post";
		
	}

}
