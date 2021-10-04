package com.mha.harrypotter.controller;

/**
 * controller to character objects
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mha.harrypotter.model.dto.CharacterDTO;
import com.mha.harrypotter.model.dto.Message;
import com.mha.harrypotter.service.CharacterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080" })
@RestController
@RequestMapping("/character")
@Api(tags = "Character")
public class CharacterController {

	@Autowired
	private CharacterService service;

	/**
	 * list all characters
	 * 
	 * @return ResponseEntity<List<CharacterDTO>>
	 */
	@GetMapping("/getAll")
	@ApiOperation(value = "list all characters")
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
	}

	/**
	 * list all character by house
	 * 
	 * @param String house name
	 * @return ResponseEntity<List<CharacterDTO>>
	 */
	@GetMapping("/getAllByHouse")
	@ApiOperation(value = "list all character by house")
	public ResponseEntity<?> getAllByHouse(@RequestParam("name") String name) {

		return new ResponseEntity<>(service.listByHouse(name), HttpStatus.OK);
	}

	/**
	 * get character by name
	 * 
	 * @param String character name
	 * @return ResponseEntity<CharacterDTO>
	 */
	@GetMapping("/getByName")
	@ApiOperation(value = "list all character by house")
	public ResponseEntity<?> getByName(@RequestParam("name") String name) {

		Optional<CharacterDTO> response = service.getByName(name);
		return response.isPresent() ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>("NOT FOUND", HttpStatus.BAD_REQUEST);
	}

	/**
	 * create a new Character
	 * 
	 * @param CharacterDTO
	 * @return ResponseEntity<Message>
	 */
	@PostMapping("/save")
	@ApiOperation(value = "create a new Character")
	public ResponseEntity<?> save(@RequestBody CharacterDTO dto) {

		Message response = service.save(dto);
		return new ResponseEntity<>(response.getMessage(), response.getStatus());

	}

	/**
	 * delete Character
	 * 
	 * @param Long id character
	 * @return ResponseEntity<Message>
	 */
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "delete Character")
	public ResponseEntity<?> delete(@PathVariable("id") Long arg0) {

		Message response = service.delete(arg0);
		return new ResponseEntity<>(response.getMessage(), response.getStatus());
	}

	/**
	 * update Character
	 * 
	 * @param Long id character
	 * @return ResponseEntity<Message>
	 */
	@PutMapping("/update/{id}")
	@ApiOperation(value = "update character")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CharacterDTO dto) {

		Message response = service.update(id, dto);
		return new ResponseEntity<>(response.getMessage(), response.getStatus());

	}

}
