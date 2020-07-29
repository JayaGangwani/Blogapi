
package com.example.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.CreatePostDao;
import com.example.dao.UserInformationDao;
import com.example.exception.PostNotCreatedException;
import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.service.CreatePostService;
import com.example.service.UserInformationService;


@RunWith(SpringRunner.class)
	class ProjectApplicationServiceTests {

		@Mock
		private UserInformationDao userInformationDao ; 
		
        @InjectMocks
		private UserInformationService userService;
		
		@Mock
		private CreatePostDao createPostDao;
		
		@InjectMocks
		private CreatePostService postService;
		
		@Test
		void shouldReturnValidId() throws Exception {
			User user=new User();
			user.setId(1l);
			List<User> userList=new ArrayList<User>();
			userList.add(user);
			given(userInformationDao.getAllUsersWithPosts()).willReturn(userList);
			assertEquals((Long)1l,userService.getAllUsersWithPosts().get(0).getId());
		}
		
		@Test
		void shouldUserInformationDataThrowException() throws Exception{
			assertThrows(UserNotFoundException.class,
		            ()->{
		            	userInformationDao.getAllUsersWithPosts();
		            });

		}
		
		@Test
		void shouldCreatePost() throws Exception{
			given(createPostDao.createPost(Mockito.anyLong(), Mockito.anyObject())).willReturn(" ");
			assertEquals("Created Post", postService.createPost(Mockito.anyLong(), Mockito.anyObject()));
		}
		
		@Test
		void shouldCreatePostCheckNullAndThrowException() throws Exception{
			
			given(createPostDao.createPost(Mockito.anyLong(), Mockito.anyObject())).willReturn(null);
			postService.createPost(Mockito.anyLong(), Mockito.anyObject());
			assertThrows(PostNotCreatedException.class,
		            ()->{
		            	postService.createPost(Mockito.anyLong(), Mockito.anyObject());
		            });
			
		}
	}



