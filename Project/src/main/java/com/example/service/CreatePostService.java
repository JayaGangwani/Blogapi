package com.example.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.exception.PostNotCreatedException;
import com.example.model.Posts;

public interface CreatePostService {

	public String createPost(Long userId,Posts post) throws PostNotCreatedException, FileNotFoundException, IOException;
}
