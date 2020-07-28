package com.example.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CreatePostDao;
import com.example.exception.PostNotCreatedException;
import com.example.model.Posts;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreatePostServiceImpl implements CreatePostService{

	@Autowired
	private CreatePostDao createPostDao;
	
	@Override
	public String createPost(Long userId, Posts post) throws FileNotFoundException, IOException {
		log.info("Create Post by Admin");
		String value=createPostDao.createPost(userId,post);
		if(null==value) {
			log.error(value + " is returned after creating post");
			throw new PostNotCreatedException("Service.createPostError");
		}
		return "Created Post";
		}

}
