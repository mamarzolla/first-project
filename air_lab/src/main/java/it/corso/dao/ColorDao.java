package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Color;

public interface ColorDao extends CrudRepository<Color, Integer> {
	Color findByColor(String color);

}
