package com.mha.harrypotter.service;

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

	/**
	 *
	 * get all houses from API and persist
	 * 
	 */
	public void getAll();

	/**
	 *
	 * save on repository by dto object
	 * 
	 * @param HouseDTO
	 * 
	 */
	public void save(HouseDTO arg0);
	
	/**
	 *
	 * find house by name
	 * 
	 * @param String - house name
	 * @return Optional<House>
	 * 
	 */
	public Optional<House> getByName(String arg0);
	
	/**
	 *
	 * find house by id
	 * 
	 * @param String - house id
	 * @return Optional<House>
	 * 
	 */
	public Optional<House> getById(String arg0);

	/**
	 *
	 * verify if house exists
	 * 
	 * @param String - house name
	 * @return boolean
	 * 
	 */
	public boolean exists(String name);

}
