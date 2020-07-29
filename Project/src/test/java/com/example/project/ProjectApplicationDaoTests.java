
	
package com.example.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.Lists;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.CreatePostDao;
import com.example.dao.UserInformationDao;
import com.example.exception.PostNotCreatedException;
import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.service.CreatePostService;
import com.example.service.UserInformationService;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
class ProjectApplicationDaoTests {

		@InjectMocks
		private UserInformationDao  userInformationDao; 
		
        
		@InjectMocks
		private CreatePostDao createPostDao;
		
		
		
		@Test
		void shouldReteriveUserData() throws Exception {
			List <User> userList = userInformationDao.getAllUsersWithPosts();
			given(userInformationDao.getAllUsersWithPosts()).willReturn(userList);
			assertEquals((Long)1l,userInformationDao.getAllUsersWithPosts().get(0).getId());
			assertEquals((Long)1l,userInformationDao.getAllUsersWithPosts().get(0).getPosts().get(0).getUserId());
		}
		
		
		
		@Test
		void shouldCreatePostForEmptyCheck() throws Exception{
			assertEquals(" ", createPostDao.createPost(Mockito.anyLong(), Mockito.anyObject()));
			}
		
		
	}



