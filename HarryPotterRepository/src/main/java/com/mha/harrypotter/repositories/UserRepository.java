package com.mha.harrypotter.repositories;

/**
 * User Repository 
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mha.harrypotter.model.User;


@Repository
public interface UserRepository	extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>, UserRepositoryFilter {
	
}