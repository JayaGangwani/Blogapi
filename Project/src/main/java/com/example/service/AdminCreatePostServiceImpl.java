package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AdminCreatePostDao;
import com.example.model.Posts;

@Service
public class AdminCreatePostServiceImpl implements AdminCreatePostService{

	@Autowired
	private AdminCreatePostDao adminCreatePostDao;
	
	@Override
	public String createPost(Long userId, Posts post) throws Exception {
		String value=adminCreatePostDao.createPost(userId,post);
		if(value==null) {
			throw new Exception("Service.createPostError");
		}
		return "Created Post";
		
	}

}
