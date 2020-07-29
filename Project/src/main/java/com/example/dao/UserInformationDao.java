package com.example.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.example.model.User;

public interface UserInformationDao {
	public List<User> getAllUsersWithPosts() throws IOException, ParseException;
}
