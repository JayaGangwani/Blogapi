package com.example.dao;

import com.example.model.Posts;

public interface AdminCreatePostDao {

	public String createPost(Long userId,Posts post);
	
}
