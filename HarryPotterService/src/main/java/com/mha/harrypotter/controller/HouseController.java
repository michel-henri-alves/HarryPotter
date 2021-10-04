package com.mha.harrypotter.controller;

import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mha.harrypotter.model.dto.Houses;

@RestController
public class HouseController {

	@Autowired
	private RestTemplate template;
	@Autowired
	private HttpEntity<?> header;

	private static final Logger log = LoggerFactory.getLogger(HouseController.class);
	private static String CONSUMING_URL = "http://us-central1-rh-challenges.cloudfunctions.net/potterApi/houses";
	private static String API_KEY = "6ffd3af5-6b2e-46da-bbd0-493b155d2a3a";

	/**
	 * retrieve list of houses availables on api
	 * 
	 * @return Optional<UserResponse>
	 */
	public Optional<Houses> getAllHouses() {

		ResponseEntity<Houses> response = null;

		try {

			response = template.exchange(CONSUMING_URL, HttpMethod.GET, this.header, Houses.class);

		} catch (Exception e) {
			log.error("Fail on communicate to API: " + e.getMessage());

		}

		return response.getStatusCode() == HttpStatus.OK ? Optional.ofNullable(response.getBody()) : Optional.empty();

	}
	

	/**
	 * 
	 * bean to build RestTemplate
	 *
	 */
	@Bean
	private RestTemplate template(RestTemplateBuilder builder) {
		return builder.build();
	}

	/**
	 * 
	 * bean to build header with parameter apiKey
	 *
	 */
	@Bean
	private HttpEntity<?> header() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("apiKey", API_KEY);

		return new HttpEntity<>(headers);

	}

}
