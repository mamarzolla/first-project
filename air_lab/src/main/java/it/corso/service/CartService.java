package it.corso.service;

import java.util.List;

import it.corso.helper.Response;
import it.corso.model.Cart;

public interface CartService {
	
	Response addItemsToCart(String tokeString, List<Cart> cartList);
	Object findCartById(String token);

}
