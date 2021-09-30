package com.mha.HarryPotterService.service;

/**
 * integration test UserService
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.mha.harrypotter.controller.UserController;
import com.mha.harrypotter.model.dto.UserRequest;
import com.mha.harrypotter.model.dto.UserResponse;
import com.mha.harrypotter.service.impl.UserServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceControllerIT {
	
	@LocalServerPort
	private int port;
	@Autowired
	private UserServiceImpl service;
	@Autowired
	private UserController controller;
	
	@Test
	public void shouldReturnUserResponse_sendUser() throws Exception {

		
		Optional<UserResponse> response = service.sendUser( Optional.of(new UserRequest("michelhenry1@gmail.com","senha","Michel Alves")));


		assertEquals(true, response.isPresent());

	}


}
