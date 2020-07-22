package com.example.dao;

import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.example.model.Posts;

@Repository
public class AdminCreatePostDaoImpl implements AdminCreatePostDao {

	@SuppressWarnings("unchecked")
	@Override
	public String createPost(Long userId, Posts post) {
		String ret = null;
		JSONObject postDetails = new JSONObject();
		postDetails.put("id", post.getId());
		postDetails.put("userId", userId);
		postDetails.put("title", post.getTitle());
		postDetails.put("body", post.getBody());
		try (FileReader postsReader = new FileReader("/Users/ASUS/Downloads/posts.json")) {
			FileWriter file = new FileWriter("/Users/ASUS/Downloads/posts.json");
			file.append(postDetails.toJSONString());
			file.close();
		} catch (Exception e) {
			return null;
		}
		return ret;
	}

}
