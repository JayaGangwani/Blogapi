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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.AdminCreatePostDao;
import com.example.dao.UserInformationPostsDao;
import com.example.model.User;
import com.example.service.AdminCreatePostService;
import com.example.service.UserInformationPostsService;

@RunWith(MockitoJUnitRunner.class)
class ProjectApplicationServiceTests {

	@Mock
	private UserInformationPostsDao  UserInformationPostsDao; 
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@InjectMocks
	private UserInformationPostsService UserInformationPostsService;
	
	@Mock
	private AdminCreatePostDao adminCreatePostDao;
	
	@InjectMocks
	private AdminCreatePostService adminCreatePostService;
	
	@Test
	void validUserData() throws Exception  {
		User user=new User();
		user.setId(1l);
		List<User> userList=new ArrayList<User>();
		userList.add(user);
		Mockito.when(UserInformationPostsDao.getAllUsersWithPosts()).thenReturn(userList);
		Assert.assertEquals((Long)1l,UserInformationPostsService.getAllUsersWithPosts().get(0).getId());
	}
	
	@Test
	void invalidUserData() throws Exception {

		exceptionRule.expect(Exception.class);
	    exceptionRule.expectMessage("Service.UserNotFound");
	    UserInformationPostsService.getAllUsersWithPosts();
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
