package com.example.service;

import com.example.model.Posts;

public interface AdminCreatePostService {

	public String createPost(Long userId,Posts post) throws Exception;
}
