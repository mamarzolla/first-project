package it.corso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Cart;
import it.corso.model.Color;
import it.corso.model.Shoe;
import it.corso.model.Size;
import it.corso.model.User;

public interface CartDao extends CrudRepository<Cart, Integer> {
	
	  Optional<Cart> findByUserAndShoeAndColorAndSize(User user, Shoe shoe, Color color,Size size);
	  List<Cart>findByUser(User user);
}
