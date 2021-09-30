package com.mha.harrypotter.service.impl;

import java.util.List;

/**
 * business logic over informations received by API
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mha.harrypotter.model.House;
import com.mha.harrypotter.service.CharacterService;
/**
 * bussiness logic for character
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

@Service
public class CharacterServiceImpl implements CharacterService {

//	@Autowired
//	private CharacterService userController;

	/**
     * 
     * @param Optional<UserRequest> data for create user access
     * @return Optional<UserResponse> 
     */
	@Override
	public Optional<Character> save(Optional<Character> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> listByHouse(House arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Optional<Character> arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Character> getById(int arg0) {
		// TODO Auto-generated method stub
		return null;
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

}
