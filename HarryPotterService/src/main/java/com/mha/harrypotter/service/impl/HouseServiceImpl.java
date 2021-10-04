package com.mha.harrypotter.service.impl;

/**
 * business logic over informations received by API
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mha.harrypotter.controller.HouseController;
import com.mha.harrypotter.model.House;
import com.mha.harrypotter.model.dto.HouseDTO;
import com.mha.harrypotter.repositories.HouseRepository;
import com.mha.harrypotter.service.HouseService;

/**
 * bussiness logic for house
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

@Service
public class HouseServiceImpl implements HouseService {

	private static final Logger log = LoggerFactory.getLogger(HouseController.class);

	@Autowired
	HouseController controller;
	@Autowired
	HouseRepository repo;

	/**
	 *
	 * call method getAll to invoke API and save registers (execute after
	 * contruction of component)
	 * 
	 */
	@PostConstruct
	public void start() {
		getAll();
	}

	@Override
	public void getAll() {

		controller.getAllHouses().ifPresentOrElse(
				// if present
				(value) -> {
					value.getHouses().stream().filter(v -> v.getName() != null).forEach(v -> {
						System.out.println(v.getName());
						save(v);
					});
				},

				// else null try again
				() -> {

					log.error("fail on obtain houses: ...trying again in five seconds");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						log.error("fail on thread: " + e.getMessage());

					}
					start();
				});

	}

	@Override
	public void save(HouseDTO arg0) {

		repo.save(new House(arg0));
	}

	@Override
	public Optional<House> getByName(String arg0) {
		
		return repo.findByName(arg0);
	}

	@Override
	public Optional<House> getById(String arg0) {
		
		return repo.findById(arg0);
	}

	@Override
	public boolean exists(String name) {

		return repo.existsByName(name);
	}

}
