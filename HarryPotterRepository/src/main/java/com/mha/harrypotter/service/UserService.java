package com.mha.harrypotter.service;

import java.util.List;

import com.mha.harrypotter.model.User;

public interface UserService {

	User findById(int arg0);

	User findByName(String arg0);

	void save(User arg0);

	void update(User arg0);

	void deleteById(int arg0);

	void deleteAll();

	List<User> findAll();

	boolean exist(User arg0);

}
