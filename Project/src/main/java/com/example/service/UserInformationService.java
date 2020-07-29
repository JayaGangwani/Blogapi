package com.example.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.example.model.User;

public interface UserInformationService {
	public List<User> getAllUsersWithPosts() throws Exception;
}
