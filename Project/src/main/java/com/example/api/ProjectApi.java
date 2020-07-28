package com.example.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import com.example.exception.PostNotCreatedException;
import com.example.model.Posts;
import com.example.model.User;
import com.example.service.CreatePostService;
import com.example.service.UserInformationService;

@RestController
@RequestMapping(value = "/projectapi")
public class ProjectApi {

	@Autowired
	private UserInformationService getAllUsersWithPosts;
	
	@Autowired
	private CreatePostService createPostService;

	@GetMapping(value = "/getusers")
	public ResponseEntity<List<User>> getUsersWithPosts() {
		try {
			return new ResponseEntity<List<User>>(getAllUsersWithPosts.getAllUsersWithPosts(), HttpStatus.OK);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}
	
	
	@PostMapping(value="/createpost/{userid}")
	public ResponseEntity<String> createPostForUser(@RequestBody Posts post,@PathVariable String userid) {
		try {
			Long userId= Long.parseLong(userid);
			createPostService.createPost(userId,post);
			return new ResponseEntity<String>("post created for userid: "+userId,HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		
	}

}
