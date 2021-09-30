package com.mha.harrypotter.controller;

import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mha.harrypotter.model.dto.Houses;
import com.mha.harrypotter.service.CharacterService;
import com.mha.harrypotter.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080" })
@RestController
@RequestMapping("/character")
@Api(tags = "Character")
public class CharacterController {


//	@Autowired
//	private CharacterService service;
//
//	/**
//     * return a joke from any category
//     * @return Optional<JokeDTO> 
//     */
//	@GetMapping("/all")
//	@ApiOperation(value = "get all characters")
//	public ResponseEntity<?> getAllCharacters() {
//
//		Optional<DTO> response = jokesService.getAnyJoke();
//		return response.isPresent() ? new ResponseEntity<>(response, HttpStatus.OK)
//				: new ResponseEntity<>(new Message("You're out of jokes"), HttpStatus.OK);
//	}
//	
//	
//	/**
//     * received rate from users
//     * 
//     * @param Optional<Integer> joke id
//     * @param Optional<Integer> grade to register (0-10)
//     * @return ResponseEntity<?> 
//     */
//	@PostMapping("/rate/{id}/{grade}")
//	@ApiOperation(value = "rate a joke. Please grades from 0 to 10 ")
//	public ResponseEntity<?> registerRate(@PathVariable Optional<Integer> id, @PathVariable Optional<Integer> grade) {
//
//		ResponseEntity<?> response = null;
//
//		//ensure grade inside range (0 to 10)
//		if (grade.get() < 0 || grade.get() > 10) {
//			response = new ResponseEntity<>(new Message("This grade is not possible:\n Vote valid: 1 to 10"),
//					HttpStatus.BAD_REQUEST);
//		}
//
//		//ensures that id belongs to a consumed register 
//		else if (jokesService.getJokeById(id.get()).isEmpty()) {
//			response = new ResponseEntity<>(new Message("This joke is not presents"), HttpStatus.BAD_REQUEST);
//		}
//
//		//register rate
//		else {
//			jokesService.registerRate(id, grade);
//			response = new ResponseEntity<>(new Message("Joke " + id.get() + " was rated"), HttpStatus.OK);
//		}
//
//		return response;
//	}
//	
//	
////	@GetMapping("/all")
////	public ResponseEntity<?> getAll() {
////
////		List<Product> list = productService.findAll();
////		
////		return new ResponseEntity<>(list, HttpStatus.OK);
////	}
////	
////	@PutMapping("/getByFilter/{page}/{size}")
////	public ResponseEntity<?> getByFilter(@PathVariable("page") int page, @PathVariable("size") int size, @Valid @RequestBody ProductDTO dto) {
////		
////		
////		PageRequest pageRequest = new PageRequest(page-1, size);
////		Specification<Product> specification = ProductSpecification.filter(entityToDTO.mappingObjects(dto, Product.class));
////		Page<Product> list = productService.list(specification, pageRequest);
////		
////		return new ResponseEntity<>(list, HttpStatus.OK);
////	}
////	
////	@GetMapping("/getById/{id}")
////	public ResponseEntity<?> getById(@PathVariable("id") Long arg0) throws EntityNotFoundException {
////
////		Product product = productService.findById(arg0);
////		ProductDTO productDTO = entityToDTO.mappingObjects(product, ProductDTO.class);
////		
////		return new ResponseEntity<>(productDTO, HttpStatus.OK);
////	}
////	
////	@PostMapping("/save")
////    public ResponseEntity<?> save(@Valid @RequestBody ProductDTO dto)
////    {
////		productService.save(entityToDTO.mappingObjects(dto, Product.class));
////		
////		return new ResponseEntity<>(dto, HttpStatus.OK);
////    }
////
////	@DeleteMapping("/delete/{id}")
////	public ResponseEntity<?> delete(@PathVariable("id") Long arg0) {
////
////		ProductDTO response = new ProductDTO();
////		Optional<Product> _produto = Optional.ofNullable(productService.findById(arg0));
////
////		if (_produto.isPresent()) {
////			response = entityToDTO.mappingObjects(_produto.get(), ProductDTO.class);
////			productService.deleteById(arg0);
////		} 
////		
////		return new ResponseEntity<>(response, HttpStatus.OK);
////	}
////	
////	@PutMapping("/update/{id}")
////	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO dto) {
////		
////		Optional<Product> _client = Optional.ofNullable(productService.findById(id));
////
////		if (_client.isPresent()) {
////			dto.setIdProduct(id);
////			productService.update(entityToDTO.mappingObjects(dto, Product.class));
////		} 
////		return new ResponseEntity<>(HttpStatus.OK);
////		
////	}

}
