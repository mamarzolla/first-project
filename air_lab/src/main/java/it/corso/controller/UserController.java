package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import it.corso.helper.PasswordValidationException;
import it.corso.helper.Response;
import it.corso.model.User;
import it.corso.schema.CreateNewUserSchema;
import it.corso.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")   // localhost:8080/shoe
@Tag(name=" User controller - Logout User controller", description ="User registration functionality, Logout functionality")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Operation
	(
			summary = " register new user",
			description =  "insert new User with addresses and profile's username and password"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = CreateNewUserSchema.class)
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "200", 
							description = "user registered correctly",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					),
					@ApiResponse( responseCode = "409", 
					description = "Username already registered",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
					),
					@ApiResponse( responseCode = "400", 
					description = "data integrity violation",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
					)
			}
			)
	
	 // localhost:8080/shoe/create
	@PostMapping("/create")
	public ResponseEntity<Response> setNewUser(@Valid @RequestBody User newUser) {
		if(!newUser.getProfile().getPassword()
				.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,50}"))
			throw new PasswordValidationException();
		Response risposta = userService.setNewUser(newUser);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);	
	}
	
	@Operation
	(
			summary = " Logout User",
			description =  "User logout Controller"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json"
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "200", 
							description = "logout succesful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					),
					@ApiResponse( responseCode = "400", 
					description = "token not found, user not logged",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
					)
			}
			)

	// localhost:8080/user/logout/{tkn}
	@DeleteMapping("/logout/{tkn}")
	public ResponseEntity<Response> logoutUser(@Parameter(description = "login token")@PathVariable("tkn")String token){
		Response risposta = userService.logout(token);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
	
	@Operation
	(
			summary = " Delete User",
			description =  "delete only User's login profile not all the data for the user"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json"
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "200", 
							description = "delete succesful",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					),
					@ApiResponse( responseCode = "400", 
					description = "token not found, user not logged",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
					)
			}
			)
	
	// localhost:8080/user/delete/{tkn}
	@DeleteMapping("/delete/{tkn}")
	public ResponseEntity<Response>deleteUser(@Parameter(description = "login token")@PathVariable("tkn") String token){
		Response risposta= userService.deleteUser(token);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
}
