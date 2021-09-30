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

import com.mha.harrypotter.model.House;


public interface CharacterService {

	Optional<Character> save(Optional<Character> arg0);
	List<Character> listAll();
	List<Character> listByHouse(House arg0);
	void update(Optional<Character> arg0, int arg1);
	Optional<Character> getById(int arg0);
	Optional<Character> getById(String arg0);
	void delete(int arg0);
	
}
