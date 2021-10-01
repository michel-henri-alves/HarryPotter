package com.mha.harrypotter.service;

import java.util.List;

/**
 * interface for characterService
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import com.mha.harrypotter.model.Character;
import com.mha.harrypotter.model.dto.CharacterDTO;



public interface CharacterService {

	List<CharacterDTO> listAll();
	void update(Optional<Character> arg0);
	Optional<Character> getById(Long arg0);
	Optional<Character> getById(String arg0);
	void delete(int arg0);
	/**
	 * update character register
	 * 
	 * @param int
	 * @param CharacterDTO
	 * 
	 */
	void update(int arg0, CharacterDTO arg1);
	/**
	 * 
	 * create new character
	 * 
	 * @param Optional<CharacterDTO>
	 * @return  
	 */
	Optional<Character> save(Optional<CharacterDTO> arg0);
	
	/**
	 * 
	 * find character by name
	 * 
	 * @param String - character name
	 * @return  Optional<Character>
	 */
	Optional<Character> getByName(String arg0);
	/**
	 * 
	 * find list of character by house
	 * 
	 * @param String - character name
	 * @return  List<CharacterDTO>
	 */
	List<CharacterDTO> listByHouse(String arg0);
	
}
