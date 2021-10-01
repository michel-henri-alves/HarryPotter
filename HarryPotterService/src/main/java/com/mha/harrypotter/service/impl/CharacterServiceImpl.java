package com.mha.harrypotter.service.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * business logic over informations received by API
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mha.harrypotter.model.Character;
import com.mha.harrypotter.model.House;
import com.mha.harrypotter.model.dto.CharacterDTO;
import com.mha.harrypotter.repositories.CharacterRepository;
import com.mha.harrypotter.service.CharacterService;
import com.mha.harrypotter.service.HouseService;
import com.mha.harrypotter.util.ConvertEntityToDTO;

/**
 * bussiness logic for character
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

@Service
public class CharacterServiceImpl implements CharacterService {

	@Autowired
	CharacterRepository repo;
	@Autowired
	HouseService houseService;
	@Autowired
	private ConvertEntityToDTO entityToDTO;

	/**
	 * 
	 * create new character
	 * 
	 * @param Optional<CharacterDTO>
	 * @return
	 */
	@Override
	public Optional<Character> save(Optional<CharacterDTO> arg0) {

		Optional<Character> response = Optional.empty();

		if (getByName(arg0.get().getName()).isEmpty()) {
			
			Character character = entityToDTO.mappingObjects(arg0.get(), Character.class);
			character.setHouse(houseService.getById(arg0.get().getHouse()).orElse(new House()));
			response = Optional.ofNullable(repo.save(character));
		}

		return response;
	}

	@Override
	public List<CharacterDTO> listAll() {
		
		List<Character> response = repo.findAll();
		return entityToDTO.mappingLists(response, CharacterDTO.class);
	}

	@Override
	public List<CharacterDTO> listByHouse(String houseName) {
		
		List<Character> response = new ArrayList<>();
		
		Optional<House> house = houseService.getByName(houseName);
		response = house.isPresent() ? repo.findByHouse(null)  : null;
		
		return entityToDTO.mappingLists(response, CharacterDTO.class);
		
	}

	/**
	 * update character register
	 * 
	 * @param int
	 * @param CharacterDTO
	 * 
	 */
	public void update(Long id, CharacterDTO arg1) {

//		// Optional<Character> _client =
//		characterRepo.findById(id).ifPresentOrElse(
//				// if present
//				(value) -> {
//
//					service.update(entityToDTO.mappingObjects(dto, Character.class));
//				},
//
//				// else
//				() -> {
//				});
//
//		if (_client.isPresent()) {
//			dto.setIdProduct(id);
//			productService.update(entityToDTO.mappingObjects(dto, Product.class));
//		}
	}

	/***
	 *
	 * find character by id
	 *
	 * @param Long id
	 *
	 * @return Optional<Character>
	 */
	@Override
	public Optional<Character> getById(Long arg0) {

		return repo.findById(arg0);
	}

	@Override
	public Optional<Character> getById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Optional<Character> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int arg0, CharacterDTO arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Character> getByName(String arg0) {
		
		return repo.findByName(arg0);
	}

}
