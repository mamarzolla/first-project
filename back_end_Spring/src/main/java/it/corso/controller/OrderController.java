package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.dto.OrderRequest;
import it.corso.helper.Response;
import it.corso.model.Cart;
import it.corso.schema.OrderRequestSchema;
import it.corso.service.OrderService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/order")  // localhost:8080/order
@Tag(name="Order Controller", description ="Order creation functionality for logged user or guest user")
public class OrderController {
	
@Autowired
private OrderService orderService;

@Operation
(
		summary = "Create order",
		description =  "create order for logged user or guest user"
		)

@io.swagger.v3.oas.annotations.parameters.RequestBody
(content = @Content(
		mediaType = "application/json",
		schema = @Schema(implementation = OrderRequestSchema.class)
		)
)@ApiResponses(
	    value = {
	            @ApiResponse(
	                responseCode = "200", 
	                description = "Order creation successful",
	                content = @Content(
	                    mediaType = "application/json",
	                    schema = @Schema(implementation = Response.class)
	                )
	            ),
	            @ApiResponse(
	                responseCode = "400", 
	                description = "Token not found, user not logged",
	                content = @Content(
	                    mediaType = "application/json",
	                    schema = @Schema(implementation = Response.class)
	                )
	            )
	        })

@PostMapping("/creation")
public ResponseEntity<Response> createLoggedOrder(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody OrderRequest orderRequest) {
Response response = orderService.createOrder(token, orderRequest.getUser(), orderRequest.getCartItems());

return ResponseEntity.status(response.getCodice()).body(response);
}}

//localhost:8080/order/create/{tkn}
//@PostMapping("create/{tkn}")

//public ResponseEntity<Response> createLoggedOrder(@RequestBody List<Cart> cartItems, 
   //         @PathVariable(name = "tkn", required = false) String token) {
//Response response = orderService.createLoggedOrder(cartItems,token);

//return ResponseEntity.status(response.getCodice()).body(response);
//}

//localhost:8080/order/create

//@PostMapping("create")

//public ResponseEntity<Response> createGuestOrder(@RequestBody OrderRequest orderRequest) {
//Response response = orderService.createGuestOrder(orderRequest.getCartItems(),orderRequest.getUser());

//return ResponseEntity.status(response.getCodice()).body(response);
//}
//}
