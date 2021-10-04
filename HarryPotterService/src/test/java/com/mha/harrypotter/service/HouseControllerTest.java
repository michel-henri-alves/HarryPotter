package com.mha.harrypotter.service;

/**
 * unit test CharacterService
 * 
 * @author michel
 * @version 0.0.1
 * 
 */
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mha.harrypotter.controller.HouseController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		com.mha.harrypotter.HarryPotterServiceApplication.class })
public class HouseControllerTest {

	@Autowired
	private HouseController controller;

	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.controller);
	}

	/**
	 * 
	 *  save success test
	 */
	@Test
	public void getAllHouses() {
		
		assertThat(controller.getAllHouses().isPresent()).isEqualTo(true);
			
	}
		
}
