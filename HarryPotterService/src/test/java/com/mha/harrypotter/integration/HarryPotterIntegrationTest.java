package com.mha.harrypotter.integration;

/**
 * integration tests
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mha.harrypotter.model.dto.CharacterDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		com.mha.harrypotter.HarryPotterServiceApplication.class })
@TestMethodOrder(OrderAnnotation.class)
public class HarryPotterIntegrationTest {

	@LocalServerPort
	private int port;
	TestRestTemplate testRestTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	CharacterDTO characterDTO = new CharacterDTO("1", "peralta", "student",
			"Hogwarts School of Witchcraft and Wizardry", "1760529f-6d51-4cb1-bcb1-25087fce5bde", "stag");

	public String getUrl(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	@Order(1)
	public void saveNewCharacterSuccess() throws Exception {

		HttpEntity<CharacterDTO> entity = new HttpEntity<>(characterDTO, headers);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getUrl("/character/save"), HttpMethod.POST,
				entity, String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	@Order(2)
	public void saveNewCharacterFailureCharacterAlreadyExist() throws Exception {

		HttpEntity<CharacterDTO> entity = new HttpEntity<>(characterDTO, headers);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getUrl("/character/save"), HttpMethod.POST,
				entity, String.class);

		assertEquals(HttpStatus.ALREADY_REPORTED, responseEntity.getStatusCode());

	}

	@Test
	@Order(3)
	public void saveNewCharacterFailureHouseNotExist() throws Exception {

		characterDTO.setHouse("1");
		HttpEntity<CharacterDTO> entity = new HttpEntity<>(characterDTO, headers);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getUrl("/character/save"), HttpMethod.POST,
				entity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

	}

	@Test
	@Order(4)
	public void listAll() throws Exception {

		ResponseEntity<CharacterDTO[]> responseEntity = testRestTemplate.getForEntity(getUrl("/character/getAll"),
				CharacterDTO[].class);

		assertEquals(1, responseEntity.getBody().length);

	}
	

	@Test
	@Order(5)
	public void listByHouseWhenRegisterExists() throws Exception {

		ResponseEntity<CharacterDTO[]> responseEntity = testRestTemplate.getForEntity(getUrl("/character/getAllByHouse?name=Gryffindor"),
				CharacterDTO[].class);

		assertEquals(1, responseEntity.getBody().length);

	}
	
	@Test
	@Order(6)
	public void listByHouseWhenRegisterNotExists() throws Exception {

		ResponseEntity<CharacterDTO[]> responseEntity = testRestTemplate.getForEntity(getUrl("/character/getAllByHouse?name=Battata"),
				CharacterDTO[].class);

		assertEquals(0, responseEntity.getBody().length);

	}

	@Test
	@Order(7)
	public void getByNameSuccess() throws Exception {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<CharacterDTO> responseEntity = testRestTemplate.exchange(getUrl("/character/getByName?name=peralta"),
				HttpMethod.GET, entity, CharacterDTO.class);
		assertEquals(characterDTO, responseEntity.getBody());
	}
	
	@Test
	@Order(8)
	public void getByNameCharacterNotFound() throws Exception {

		
		HttpEntity<String> entity = new HttpEntity<>("peralta", headers);
		ResponseEntity<CharacterDTO> responseEntity = testRestTemplate.exchange(getUrl("/character/getByName"),
				HttpMethod.GET, entity, CharacterDTO.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	
	@Test
	@Order(9)
	public void updateSuccess() throws Exception {

		
		characterDTO.setName("boyle");
		HttpEntity<CharacterDTO> entity = new HttpEntity<>(characterDTO, headers);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getUrl("/character/update/1"), HttpMethod.PUT,
				entity, String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}
	
	@Test
	@Order(10)
	public void updateFailHouseNotExist() throws Exception {

		
		characterDTO.setHouse("qqeqew");
		HttpEntity<CharacterDTO> entity = new HttpEntity<>(characterDTO, headers);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getUrl("/character/update/1"), HttpMethod.PUT,
				entity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

	}
	
	@Test
	@Order(11)
	public void updateCharacterNotExist() throws Exception {

		
		characterDTO.setName("dias");
		HttpEntity<CharacterDTO> entity = new HttpEntity<>(characterDTO, headers);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getUrl("/character/update/32"), HttpMethod.PUT,
				entity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

	}
	
	@Test
	@Order(12)
	public void deleteSuccess() throws Exception {

		HttpEntity<CharacterDTO> entity = new HttpEntity<>(characterDTO, headers);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getUrl("/character/delete/1"), HttpMethod.DELETE,
				entity, String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}	
	
	
	
	@Test
	@Order(13)
	public void deleteFailCharacterNotExist() throws Exception {

		HttpEntity<CharacterDTO> entity = new HttpEntity<>(characterDTO, headers);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getUrl("/character/delete/1"), HttpMethod.DELETE,
				entity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

	}
	
	
}
