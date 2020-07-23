package com.example.api;

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

import com.example.model.Posts;
import com.example.model.User;
import com.example.service.AdminCreatePostService;
import com.example.service.UserInformationPostsService;

@RestController
@RequestMapping(value = "/projectAPI")
public class ProjectApi {

	@Autowired
	private UserInformationPostsService getAllUsersWithPosts;
	
	@Autowired
	private AdminCreatePostService adminCreatePostService;

	@GetMapping(value = "/getUsers")
	public ResponseEntity<List<User>> getUsersWithPosts() {
		try {
			return new ResponseEntity<List<User>>(getAllUsersWithPosts.getAllUsersWithPosts(), HttpStatus.OK);
		} catch (HttpStatusCodeException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}
	
	//@RequestMapping(value = "/createPost/{userId}", method = RequestMethod.POST)
	@PostMapping(value="/createPost/{UserId}")
	public ResponseEntity<String> createPostForUser(@RequestBody Posts post,@PathVariable String UserId){
		try {
			Long userId= Long.parseLong(UserId);
			adminCreatePostService.createPost(userId,post);
			return new ResponseEntity<String>("post created for userid: "+userId,HttpStatus.OK);
		}catch(ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		
	}

}
