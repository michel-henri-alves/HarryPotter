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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mha.harrypotter.model.Character;
import com.mha.harrypotter.model.House;
import com.mha.harrypotter.model.dto.CharacterDTO;
import com.mha.harrypotter.model.dto.Message;
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

	@Override
	public Message save(CharacterDTO arg0) {

		Message message = new Message();
		Optional<House> house = houseService.getById(arg0.getHouse());

		boolean isNewCharacter = getByName(arg0.getName()).isEmpty();// register already exist?
		boolean houseExist = house.isPresent();// house exist?

		// case success
		if (isNewCharacter && houseExist) {

			// if house exist
			Character newCharacter = entityToDTO.mappingObjects(arg0, Character.class);// convert dto to object
			newCharacter.setHouse(house.get());
			Optional<Character> character = Optional.ofNullable(repo.save(newCharacter));
			message = character.isPresent()
					? new Message("Success, " + character.get().getName() + " created!", HttpStatus.OK)
					: new Message("Fail ", HttpStatus.BAD_REQUEST);
		}

		// case fail
		else {

			if (!isNewCharacter)
				message = new Message("Register already exist", HttpStatus.ALREADY_REPORTED);
			if (!houseExist)
				message = new Message("House not exist", HttpStatus.BAD_REQUEST);

		}

		return message;
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
		response = house.isPresent() ? repo.findByHouse(house.get()) : new ArrayList<>();

		return entityToDTO.mappingLists(response, CharacterDTO.class);

	}

	@Override
	public Optional<Character> getById(Long arg0) {

		return repo.findById(arg0);
	}

	@Override
	public Message delete(Long arg0) {

		Message message = new Message();
		Optional<Character> character = repo.findById(arg0.intValue());

		if (character.isPresent()) {

			repo.delete(character.get());
			message = new Message("Success", HttpStatus.OK);
		}

		else {

			message = new Message("Register not exist", HttpStatus.BAD_REQUEST);
		}

		return message;

	}


	@Override
	public Message update(Long arg0, CharacterDTO arg1) {

		Message message = new Message();
		Optional<House> house = houseService.getById(arg1.getHouse());
		Optional<Character> character = repo.findById(arg0.intValue());

		boolean characterExist = character.isPresent();// register already exist?
		boolean houseExist = house.isPresent();// house exist?

		// case success
		if (characterExist && houseExist) {

			// if house exist
			Character _character = entityToDTO.mappingObjects(arg1, Character.class);
			_character.setId(arg0.intValue());
			_character.setHouse(house.get());
			repo.save(_character);
			message = new Message("Success", HttpStatus.OK);
		}

		// case fail
		else {

			if (!characterExist)
				message = new Message("Register not exist", HttpStatus.BAD_REQUEST);
			if (!houseExist)
				message = new Message("House not exist", HttpStatus.BAD_REQUEST);

		}

		return message;
	}

	@Override
	public Optional<CharacterDTO> getByName(String arg0) {

		Optional<Character> _character = repo.findByName(arg0);
		
		Optional<CharacterDTO> response = (_character.isPresent())
				? Optional.ofNullable(entityToDTO.mappingObjects(repo.findByName(arg0).get(), CharacterDTO.class))
				: Optional.empty();


		return response;
	}


}
