package com.example.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.Lists;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.AdminCreatePostDao;
import com.example.dao.getAllUsersWithPostsDao;
import com.example.model.User;
import com.example.service.AdminCreatePostService;
import com.example.service.getAllUsersWithPostsService;


class ProjectApplicationServiceTests {

	@Autowired
	private getAllUsersWithPostsDao  getAllUsersWithPostsDao; 
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Autowired
	private getAllUsersWithPostsService getAllUsersWithPostsService;
	
	@Autowired
	private AdminCreatePostDao adminCreatePostDao;
	
	@Autowired
	private AdminCreatePostService adminCreatePostService;
	
	@Test
	void validUserData() throws Exception {
		User user=new User();
		user.setId(1l);
		List<User> userList=new ArrayList<User>();
		userList.add(user);
		Mockito.when(getAllUsersWithPostsDao.getAllUsersWithPosts()).thenReturn(userList);
		Assert.assertEquals((Long)1l,getAllUsersWithPostsService.getAllUsersWithPosts().get(0).getId());
	}
	
	@Test
	void invalidUserData() throws Exception {

		exceptionRule.expect(Exception.class);
	    exceptionRule.expectMessage("Service.UserNotFound");
	    getAllUsersWithPostsService.getAllUsersWithPosts();
	}

	@Test
	void validPost() throws Exception{
		Mockito.when(adminCreatePostDao.createPost(Mockito.anyLong(), Mockito.anyObject())).thenReturn(" ");
		Assert.assertEquals("Created Post", adminCreatePostService.createPost(Mockito.anyLong(), Mockito.anyObject()));
	}
	
	@Test
	void invalidPost() throws Exception{
		exceptionRule.expect(Exception.class);
	    exceptionRule.expectMessage("Service.createPostError");

		Mockito.when(adminCreatePostDao.createPost(Mockito.anyLong(), Mockito.anyObject())).thenReturn(null);
		adminCreatePostService.createPost(Mockito.anyLong(), Mockito.anyObject());
	}
}
