package com.masaicalender.service;

import com.masaicalender.exception.AuthorizationException;
import com.masaicalender.exception.UserException;
import com.masaicalender.model.LoginDTO;
import com.masaicalender.model.User;

public interface UserService {
	public User registerUser(User user) throws UserException;
	
	public String loginUser(LoginDTO dto) throws UserException;
	
	public User updateCredentials(User user, String uniqueId) throws UserException, AuthorizationException;
}
