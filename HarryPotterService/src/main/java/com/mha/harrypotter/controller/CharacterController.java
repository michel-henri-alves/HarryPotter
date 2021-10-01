package com.mha.harrypotter.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mha.harrypotter.model.dto.CharacterDTO;
import com.mha.harrypotter.model.dto.Message;
import com.mha.harrypotter.service.CharacterService;
import com.mha.harrypotter.util.ConvertEntityToDTO;

import io.swagger.annotations.Api;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080" })
@RestController
@RequestMapping("/character")
@Api(tags = "Character")
public class CharacterController {

	@Autowired
	private CharacterService service;

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
	}
	
	@PutMapping("/allByHouse/{name}")
	public ResponseEntity<?> getAllByHouse(@PathVariable("name") String name) {

		return new ResponseEntity<>(service.listByHouse(name), HttpStatus.OK);
	}
//
//	@PutMapping("/getByFilter/{page}/{size}")
//	public ResponseEntity<?> getByFilter(@PathVariable("page") int page, @PathVariable("size") int size,
//			@Valid @RequestBody ProductDTO dto) {
//
//		PageRequest pageRequest = new PageRequest(page - 1, size);
//		Specification<Product> specification = ProductSpecification
//				.filter(entityToDTO.mappingObjects(dto, Product.class));
//		Page<Product> list = productService.list(specification, pageRequest);
//
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}
//
//	@GetMapping("/getById/{id}")
//	public ResponseEntity<?> getById(@PathVariable("id") Long arg0) {
//
//		service.getById(arg0);
//		return new ResponseEntity<>(productDTO, HttpStatus.OK);
//	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CharacterDTO dto) {

		return service.save(Optional.ofNullable(dto)).isPresent() ? new ResponseEntity<>(new Message("Success"), HttpStatus.OK)
						: new ResponseEntity<>(new Message("User duplicated"), HttpStatus.ALREADY_REPORTED);

	}

//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> delete(@PathVariable("id") Long arg0) {
//
//		ProductDTO response = new ProductDTO();
//		Optional<Product> _produto = Optional.ofNullable(productService.findById(arg0));
//
//		if (_produto.isPresent()) {
//			response = entityToDTO.mappingObjects(_produto.get(), ProductDTO.class);
//			productService.deleteById(arg0);
//		}
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
//	@PutMapping("/update/{id}")
//	@ApiOperation(value = "update character")
//	//public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody CharacterDTO dto) {
//	public void update(@PathVariable("id") Long id, @RequestBody CharacterDTO dto) {
//		
//		service.getById(0)
//		service.update(id, dto);
//		//return new ResponseEntity<>(HttpStatus.OK);
//		
//	}

}
