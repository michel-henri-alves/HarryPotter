package com.mha.HarryPotterService.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceControllerIT {
	
	@LocalServerPort
	private int port;
//	@Autowired
//	private UserServiceImpl service;
//	@Autowired
//	private UserController controller;
	
	@Test
	public void shouldReturnUserResponse_sendUser() throws Exception {

		
//		Optional<UserResponse> response = service.sendUser( Optional.of(new UserRequest("michelhenry1@gmail.com","senha","Michel Alves")));
//
//
//		assertEquals(true, response.isPresent());

	}


}
