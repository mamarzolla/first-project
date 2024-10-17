package it.corso.dto;

import java.util.List;

import it.corso.model.Cart;
import it.corso.model.User;

public class OrderRequest {
	private List<Cart> cartItems;
	private User user;
	public List<Cart> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<Cart> cartItems) {
		this.cartItems = cartItems;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
