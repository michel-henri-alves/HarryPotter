package com.mha.harrypotter.util;

/**
 * unit test ConvertEntityToDTO
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mha.harrypotter.model.Character;
import com.mha.harrypotter.model.House;
import com.mha.harrypotter.model.dto.CharacterDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		com.mha.harrypotter.HarryPotterServiceApplication.class })
public class ConvertEntityToDTOTest {

	@Autowired
	private ConvertEntityToDTO convertEntityToDTO;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.convertEntityToDTO);
	}

	/**
	 * convert object to dto
	 */
	@Test
	public void convertObjectToDTO() {
		
		Optional<House> house = Optional.of(new House("1760529f-6d51-4cb1-bcb1-25087fce5bde", "Gryffindor", "Minerva McGonagall", List.of("courage", "bravery", "nerve", "chivalry"), List.of("scarlet", "gold"), "Hogwarts School of Witchcraft and Wizardry", "lion", "Nearly Headless Nick", "Goderic Gryffindor"));
		CharacterDTO expected = new CharacterDTO("1", "peralta", "student", "Hogwarts School of Witchcraft and Wizardry", "1760529f-6d51-4cb1-bcb1-25087fce5bde", "stag");
		Character character = new Character(1, "peralta", "student", "Hogwarts School of Witchcraft and Wizardry", house.get(), "stag");

		assertThat(convertEntityToDTO.mappingObjects(character, CharacterDTO.class)).usingRecursiveComparison().isEqualTo(expected);
		
	}
	
	
	/**
	 * convert object list to dto list
	 * 
	 */
	@Test
	public void convertObjectListToDTOList() {
		
		Optional<House> house = Optional.of(new House("1760529f-6d51-4cb1-bcb1-25087fce5bde", "Gryffindor", "Minerva McGonagall", List.of("courage", "bravery", "nerve", "chivalry"), List.of("scarlet", "gold"), "Hogwarts School of Witchcraft and Wizardry", "lion", "Nearly Headless Nick", "Goderic Gryffindor"));
		CharacterDTO dto1 = new CharacterDTO("1", "peralta", "student", "Hogwarts School of Witchcraft and Wizardry", "1760529f-6d51-4cb1-bcb1-25087fce5bde", "stag");
		CharacterDTO dto2 = new CharacterDTO("2", "boyle", "student", "Hogwarts School of Witchcraft and Wizardry", "1760529f-6d51-4cb1-bcb1-25087fce5bde", "stag");	
		Character character1 = new Character(1, "peralta", "student", "Hogwarts School of Witchcraft and Wizardry", house.get(), "stag");
		Character character2 = new Character(2, "boyle", "student", "Hogwarts School of Witchcraft and Wizardry", house.get(), "stag");
		
		
		assertThat(convertEntityToDTO.mappingLists(List.of(character1, character2), CharacterDTO.class)).usingRecursiveComparison().isEqualTo(List.of(dto1, dto2));
		
	}

}
