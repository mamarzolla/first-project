package it.corso.schema;

import java.util.List;

import it.corso.dto.CartDto;

public class AddToCartSchema {
	private List<CartDto> cart;

	public List<CartDto> get() {
		return cart;
	}

	public void setKeyNotRequired(List<CartDto> cart) {
		this.cart = cart;
	}

}
