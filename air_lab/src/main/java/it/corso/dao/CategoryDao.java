package it.corso.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Category;

public interface CategoryDao extends CrudRepository<Category, Integer> {
	
	Optional<Category> findByName(String name);

	

}
