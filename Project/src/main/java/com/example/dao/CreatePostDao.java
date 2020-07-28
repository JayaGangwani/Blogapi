package com.example.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.exception.PostNotCreatedException;
import com.example.model.Posts;

public interface CreatePostDao {

	public String createPost(Long userId,Posts post) throws FileNotFoundException, IOException ;
	
}
