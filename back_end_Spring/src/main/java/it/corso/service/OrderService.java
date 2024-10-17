package it.corso.service;

import java.util.List;

import it.corso.helper.Response;
import it.corso.model.Cart;
import it.corso.model.User;

public interface OrderService {
	 Response createOrder(String token,User guestUser, List<Cart> cartItems);
	 Response createLoggedOrder(List<Cart> cartItems,String token);

	 Response createGuestOrder(List<Cart> cartItems, User user);

}
