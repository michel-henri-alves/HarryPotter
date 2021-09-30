package com.mha.harrypotter.service;

/**
 * interface for userService
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import com.mha.harrypotter.model.dto.UserRequest;
import com.mha.harrypotter.model.dto.UserResponse;


public interface UserService {

	public Optional<UserResponse> sendUser(Optional<UserRequest> request);
	
}
