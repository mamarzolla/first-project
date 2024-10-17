package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Size;

public interface SizeDao extends CrudRepository<Size, Integer>{
	Size findBySize(String size);
}
