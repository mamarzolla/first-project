package it.corso.schema;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import it.corso.dto.CartDto;
@Schema(description = "Create order request schema")
public class OrderRequestSchema {
	@Schema(description = "Existing cart or guest cart always required")
	private List<CartDto> cartItems;
	@Schema(description = "guest User Details not required if the token is present")
	private orderGuestUser user;
	public List<CartDto> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartDto> cartItems) {
		this.cartItems = cartItems;
	}
	public orderGuestUser getUser() {
		return user;
	}
	public void setUser(orderGuestUser user) {
		this.user = user;
	}

}
