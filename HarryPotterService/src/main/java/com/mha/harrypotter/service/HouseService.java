package com.mha.harrypotter.service;

import java.util.List;

/**
 * interface for HouseService
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import com.mha.harrypotter.model.House;
import com.mha.harrypotter.model.dto.HouseDTO;

public interface HouseService {

	Optional<House> save(Optional<House> arg0);

	List<House> listAll();

	void update(Optional<House> arg0, int arg1);

	Optional<House> getByName(String arg0);

	void delete(int arg0);

	/**
	 *
	 * save on repository by dto object
	 * 
	 * @param HouseDTO
	 * 
	 */
	void save(HouseDTO arg0);

	/**
	 *
	 * save all houses retrieved by potterapi
	 * 
	 * 
	 */
	void getAll();

	/**
	 * find house by name
	 *
	 * @param String - house id
	 * @return Optional<House>
	 * 
	 */
	Optional<House> getById(String arg0);
}
