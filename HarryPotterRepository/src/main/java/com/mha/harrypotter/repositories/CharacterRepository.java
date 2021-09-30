package com.mha.harrypotter.repositories;

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



@Repository
public interface CharacterRepository extends PagingAndSortingRepository<Character, Long>, JpaSpecificationExecutor<Character>, CharacterRepositoryFilter {
	
}