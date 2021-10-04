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
import com.mha.harrypotter.model.dto.Message;

public interface CharacterService {

	/**
	 * 
	 * create new character
	 * 
	 * @param Optional<CharacterDTO>
	 * @return
	 */
	public Message save(CharacterDTO arg0);

	/**
	 *
	 * lista all characters
	 * 
	 * @return List<CharacterDTO>
	 *
	 */
	public List<CharacterDTO> listAll();

	/**
	 *
	 * lista all characters filtered by house
	 * 
	 * @param String
	 * @return List<CharacterDTO>
	 *
	 */
	public List<CharacterDTO> listByHouse(String houseName);

	/**
	 *
	 * find character by id
	 *
	 * @param Long id
	 * @return Optional<Character>
	 * 
	 */
	public Optional<Character> getById(Long arg0);

	/**
	 *
	 * delete character by id
	 *
	 * @param Long id
	 * @return Message
	 * 
	 */
	public Message delete(Long arg0);

	/**
	 *
	 * update character
	 *
	 * @param Long id to update
	 * @param CharacterDTO
	 * @return Message
	 * 
	 */
	public Message update(Long arg0, CharacterDTO arg1);

	/**
	 *
	 * retrieve Character byName
	 *
	 * @param String
	 * @return Optional<CharacterDTO>
	 * 
	 */
	public Optional<CharacterDTO> getByName(String arg0);

}
