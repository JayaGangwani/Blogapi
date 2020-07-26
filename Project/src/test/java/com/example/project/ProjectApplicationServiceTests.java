package com.example.project;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.dao.AdminCreatePostDao;
import com.example.model.User;
import com.example.service.AdminCreatePostService;
import com.example.service.AdminCreatePostServiceImpl;
import com.example.service.UserInformationPostsService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProjectApplicationServiceTests {
	
	@Autowired                           
    private MockMvc mockMvc;  
                                                 
    @MockBean                           
    private AdminCreatePostService userService; 
    
    @MockBean
    private  UserInformationPostsService postService;
	
	
    @Test
	public void shouldFetchAllUser() throws Exception
	{
		List<User> UserWithPosts = postService.getAllUsersWithPosts();
		given(postService.getAllUsersWithPosts()).willReturn(UserWithPosts);
		this.mockMvc.perform(get("/projectAPI/getUsers"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is (UserWithPosts.size())));
	}
	
	@Test
	public void invalidUserData() throws Exception {
	this.mockMvc.perform(get("/projectAPI/getUsers")
     .contentType(MediaType.APPLICATION_JSON))
     .andExpect(status().isBadRequest())
	 .andExpect(result -> assertTrue(result.getResolvedException() instanceof NullPointerException))
	 .andExpect(result -> assertEquals("Service.UserNotFound", result.getResolvedException().getMessage()));
	}
	
	
	@Test
	public void shouldCreatePostSuccessfully() throws Exception
	{
	given(userService.createPost(Mockito.anyLong(), Mockito.anyObject())).willReturn("Created Post");
	Assert.assertEquals("Created Post", userService.createPost(Mockito.anyLong(), Mockito.anyObject()));
	}
	
	@Test
	public void invalidPost() throws Exception{
		given(userService.createPost(Mockito.anyLong(), Mockito.anyObject())).willReturn(null);
		Long UserId =1L;
		this.mockMvc.perform(post("/projectAPI/createPost/{UserId}", UserId)
		.contentType(MediaType.APPLICATION_JSON)
		.content(userService.createPost(Mockito.anyLong(), Mockito.anyObject())))
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof NullPointerException))
		.andExpect(result -> assertEquals("Service.createPostError", result.getResolvedException().getMessage()));
			       


	}
	

}
