package it.corso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Category;
import it.corso.model.Shoe;



public interface ShoeDao extends CrudRepository<Shoe, Integer> {
	
	Optional<Shoe> findByName(String name); 
	List<Shoe> findByCategory(Category category);
	Shoe findByImg(String imageName);
}
