package com.mha.harrypotter.service;

/**
 * unit test CharacterService
 * 
 * @author michel
 * @version 0.0.1
 * 
 */
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.mha.harrypotter.model.Character;
import com.mha.harrypotter.model.House;
import com.mha.harrypotter.model.dto.CharacterDTO;
import com.mha.harrypotter.repositories.CharacterRepository;
import com.mha.harrypotter.service.impl.CharacterServiceImpl;
import com.mha.harrypotter.util.ConvertEntityToDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		com.mha.harrypotter.HarryPotterServiceApplication.class })
public class CharacterServiceTest {

	@Autowired
	private CharacterServiceImpl service;
	@MockBean
	private CharacterRepository repo;
	@MockBean
	private HouseService houseService;
	@Autowired
	private ConvertEntityToDTO entityToDTO;

	Optional<House> house = Optional.of(new House("1760529f-6d51-4cb1-bcb1-25087fce5bde", "Gryffindor", "Minerva McGonagall", List.of("courage", "bravery", "nerve", "chivalry"), List.of("scarlet", "gold"), "Hogwarts School of Witchcraft and Wizardry", "lion", "Nearly Headless Nick", "Goderic Gryffindor"));
	Optional<Character> character = Optional.of(new Character(1, "TONY STARK", "BILLIONARY", "S.H.I.E.L.D", house.get(), "NICK FURY"));
	CharacterDTO characterDTO = new CharacterDTO("1", "peralta", "student", "Hogwarts School of Witchcraft and Wizardry", "1760529f-6d51-4cb1-bcb1-25087fce5bde", "stag");
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.service);
	}

	/**
	 * 
	 *  save success test
	 */
	@Test
	public void saveSuccessTest() {
		
		Character character = entityToDTO.mappingObjects(characterDTO, Character.class);
		character.setHouse(house.get());
		
		when(houseService.getById(characterDTO.getHouse())).thenReturn(house);
		when(service.getByName(characterDTO.getName())).thenReturn(Optional.empty());
		when(repo.save(character)).thenReturn(character);
		
		assertEquals(HttpStatus.OK, service.save(characterDTO).getStatus());
	}
	
	/**
	 * save failure - house not exist
	 */
	@Test
	public void saveHouseNotExistTest() {
	
		Character character = entityToDTO.mappingObjects(characterDTO, Character.class);
		character.setHouse(house.get());
		
		when(houseService.getById(characterDTO.getHouse())).thenReturn(Optional.empty());
		when(service.getByName(characterDTO.getName())).thenReturn(Optional.empty());
		when(repo.save(character)).thenReturn(character);
		
		assertEquals("House not exist", service.save(characterDTO).getMessage());
	
	}
	
	/**
	 * save failure - register already exist
	 */
	@Test
	public void saveCharacterAlreadyExistTest() {
	
		Character character = entityToDTO.mappingObjects(characterDTO, Character.class);
		character.setHouse(house.get());
		
		when(houseService.getById(characterDTO.getHouse())).thenReturn(house);
		when(service.getByName(characterDTO.getName())).thenReturn(Optional.of(characterDTO));
		when(repo.save(character)).thenReturn(character);
		
		assertEquals("Register already exist", service.save(characterDTO).getMessage());
	
	}
	
	/**
	 * delete success
	 */
	@Test
	public void deleteSuccessTest() {
		
		when(repo.findById(1)).thenReturn(character);

		assertEquals(HttpStatus.OK, service.delete(1L).getStatus());
		
	}
	
	/**
	 * delete failure - register not present
	 */
	@Test
	public void deleteRegisterNotPresentTest() {
		
		when(repo.findById(1)).thenReturn(Optional.empty());

		assertEquals("Register not exist", service.delete(1L).getMessage());
		
	}
	
	/**
	 * update success
	 */
	@Test
	public void updateSuccess() {
		
		when(houseService.getById(characterDTO.getHouse())).thenReturn(house);
		when(repo.findById(1)).thenReturn(character);
		
		assertEquals(HttpStatus.OK, service.update(1L, characterDTO).getStatus());
		
	}
	
	/**
	 * update failure - register not exist
	 */
	@Test
	public void updateRegisterNotExist() {
		
		when(houseService.getById(characterDTO.getHouse())).thenReturn(house);
		when(repo.findById(1)).thenReturn(Optional.empty());
		
		assertEquals("Register not exist", service.update(1L, characterDTO).getMessage());
		
	}
	
	/**
	 * update failure - house not exist
	 */
	@Test
	public void updateHouseNotExist() {
		
		when(houseService.getById(characterDTO.getHouse())).thenReturn(Optional.empty());
		when(repo.findById(1)).thenReturn(character);
		
		assertEquals("House not exist", service.update(1L, characterDTO).getMessage());
		
	}
	
}
