package com.masaicalender.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masaicalender.exception.AuthorizationException;
import com.masaicalender.exception.UserException;
import com.masaicalender.model.CurrentUserSession;
import com.masaicalender.model.LoginDTO;
import com.masaicalender.model.User;
import com.masaicalender.repository.SessionDao;
import com.masaicalender.repository.UserDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionDao sessionDao;

	@Override
	public User registerUser(User user) throws UserException {
		
		Optional<User> existingUser = userDao.findById(user.getEmail());
		
		if(!existingUser.isPresent()) {
			return userDao.save(user);
		}else {
			throw new UserException("User already registered");
		}
		
	}

	@Override
	public String loginUser(LoginDTO dto) throws UserException {
		Optional<User> existingUser = userDao.findById(dto.getEmail());
		
		if(!existingUser.isPresent()) {
			throw new UserException("Please, register yourself first!");
		}
		
		Optional<CurrentUserSession> loggedinUser = sessionDao.findById(existingUser.get().getEmail());
		
		if(loggedinUser.isPresent()) {
			throw new AuthorizationException("User already logged in");
		}
		
		if(existingUser.get().getPassword().equals(dto.getPassword())) {
			String key = RandomString.make(6);
			
			CurrentUserSession currentUser = new CurrentUserSession(existingUser.get().getEmail(),key,LocalDateTime.now());
		
			sessionDao.save(currentUser);
			
			return currentUser.toString();
		}
		else {
			throw new AuthorizationException("Please enter a valid password");
		}
	}

	@Override
	public User updateCredentials(User user, String uniqueId) throws UserException, AuthorizationException {
		
		CurrentUserSession loggedinUser = sessionDao.findByUniqueId(uniqueId);
		
		if(loggedinUser==null) {
			throw new AuthorizationException("Please login first");
		}
		
		if(loggedinUser.getEmail()==user.getEmail()) {
			return userDao.save(user);
		}else {
			throw new AuthorizationException("Invalid credentials!");
		}
	}

}
