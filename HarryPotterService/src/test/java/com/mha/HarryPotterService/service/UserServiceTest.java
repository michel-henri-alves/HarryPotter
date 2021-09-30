package com.mha.HarryPotterService.service;

/**
 * unit test UserService
 * 
 * @author michel
 * @version 0.0.1
 * 
 */
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mha.harrypotter.controller.UserController;
import com.mha.harrypotter.model.dto.UserRequest;
import com.mha.harrypotter.model.dto.UserResponse;
import com.mha.harrypotter.service.impl.UserServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
	
	@Autowired
	private UserServiceImpl userService;
	@MockBean
	private UserController userController;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.userService);
	}
	
	@Test
	public void success_sendUser() {
		
		Optional<UserRequest> request = Optional.of(new UserRequest("michelhenry1@gmail.com","senha","Michel Alves"));
		Optional<UserResponse> response = Optional.of(new UserResponse("michelhenry1@gmail.com","senha", 1, "abc-1234", "Michel Alves"));
		
		// mock da classe service
		when(this.userService.sendUser(request))
						.thenReturn(response);
		
	}
}
