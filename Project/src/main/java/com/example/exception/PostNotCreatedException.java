package com.example.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PostNotCreatedException extends IOException {

	public PostNotCreatedException(String message) {
		super(message);
		
	}

}
