package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.helper.Response;
import it.corso.model.Cart;
import it.corso.schema.AddToCartSchema;
import it.corso.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/cart")
@Tag(name="Cart Controller",description = "User logged Cart control functionality")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Operation
	(
			summary = "Add to cart only logged user ",
			description =  "cart request management"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = AddToCartSchema.class)
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "200", 
							description = "shoes list added to user cart",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					),
					@ApiResponse( responseCode = "404", 
					description = "object properties not found or not available defined on the single response",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
					),
					@ApiResponse( responseCode = "401", 
					description = "shoe not found",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
			)
			}
			)
	
	 // localhost:8080/cart/add/{tkn}
	@PostMapping("/add/{tkn}")
	public ResponseEntity<Response> addToCartLoggedUser(@Parameter( description = "authentiucation token")@PathVariable("tkn")String token,@RequestBody List<Cart> cart){
		Response resp = cartService.addItemsToCart(token, cart);
		return ResponseEntity.status(resp.getCodice()).body(resp);
	}
	@Operation
	(
			summary = "find user's cart by login Token",
			description =  "cart request management"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json"
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "200", 
							description = "list User's cart",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = AddToCartSchema.class)
									)
					),
					@ApiResponse( responseCode = "400", 
					description = "user not logged",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
				)
			)
			}
			)
	
	//localhost:8080/cart//find/{tkn}
	@GetMapping("/find/{tkn}")
	public ResponseEntity<Object>getCartListByToken(@PathVariable("tkn")String token) {	

	    Object resp = cartService.findCartById(token);
	    if (resp instanceof Response) {
	        Response errorResponse = (Response) resp;
	        return ResponseEntity.status(errorResponse.getCodice()).body(errorResponse);
	    }
	        return ResponseEntity.status(HttpStatus.OK).body(resp);
	
	}
	

}
