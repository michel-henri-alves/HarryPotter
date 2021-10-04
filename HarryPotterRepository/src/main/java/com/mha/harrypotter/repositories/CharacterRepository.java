package com.mha.harrypotter.repositories;

/**
 * 
 * character repository
 * 
 * @author michel
 * 
 */

import java.util.List;
import java.util.Optional;

/**
 * Character Repository 
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mha.harrypotter.model.Character;
import com.mha.harrypotter.model.House;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<Character, Long>,
		JpaSpecificationExecutor<Character>, CharacterRepositoryFilter {

	Optional<Character> findByName(String name);

	Optional<Character> findById(int id);

	List<Character> findAll();

	List<Character> findByHouse(House house);

}