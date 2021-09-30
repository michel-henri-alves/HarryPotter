package com.mha.harrypotter.controller;

/**
 *  received data by API
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mha.harrypotter.model.dto.UserRequest;
import com.mha.harrypotter.model.dto.UserResponse;

@RestController
public class UserController {

	@Autowired
	private RestTemplate template;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private static String CONSUMING_URL = "http://us-central1-rh-challenges.cloudfunctions.net/potterApi/users";

	/**
	 * send user to obtain token
	 * @param Optional<UserRequest> data for create user access
	 * @return Optional<UserResponse>
	 */
	public Optional<UserResponse> sendUser(Optional<UserRequest> request) {

		Optional<UserResponse> response = Optional.ofNullable(null);

		try {

			response = Optional.ofNullable(template.postForEntity(CONSUMING_URL,
					request.get(), UserResponse.class)
					.getBody());

		} catch (Exception e) {
			log.error("Fail on communicate to API: " + e.getMessage());

		}
		return response;

	}

}
