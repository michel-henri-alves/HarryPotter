package com.mha.harrypotter.service.impl;

/**
 * business logic over informations received by API
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mha.harrypotter.controller.UserController;
import com.mha.harrypotter.model.dto.UserRequest;
import com.mha.harrypotter.model.dto.UserResponse;


@Service
public class UserServiceImpl {

	@Autowired
	private UserController userController;

	/**
     * 
     * @param Optional<UserRequest> data for create user access
     * @return Optional<UserResponse> 
     */
	public Optional<UserResponse> sendUser(Optional<UserRequest> request) {
		
		return userController.sendUser(request.or(() -> Optional.of(new UserRequest())));

	}

}
