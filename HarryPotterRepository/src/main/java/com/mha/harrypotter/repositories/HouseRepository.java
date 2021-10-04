package com.mha.harrypotter.repositories;

/**
 * 
 * house repository
 * 
 * @author michel
 * 
 */

import java.util.Optional;

/**
 * House Repository 
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mha.harrypotter.model.House;

@Repository
public interface HouseRepository
		extends PagingAndSortingRepository<House, Long>, JpaSpecificationExecutor<House>, HouseRepositoryFilter {

	Optional<House> findById(String arg0);

	Optional<House> findByName(String arg0);

	boolean existsByName(String arg0);
}