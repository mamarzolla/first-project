package it.corso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.helper.Response;
import it.corso.schema.LoginUserSchema;
import it.corso.service.ProfileService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/user")       // localhost:8080/user
@Tag(name="User login Controller",description = "User login control functionality")
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	@Operation(
			summary = " User Login",
			description = ""
			
			) 
	@io.swagger.v3.oas.annotations.parameters.RequestBody 
	(content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = LoginUserSchema.class)))

	@ApiResponses 
	(value = {
			@ApiResponse(responseCode ="200", description ="user athenticated - Return string authentication token ", 
					content=@Content
					(mediaType = "application/json",
					schema = @Schema(implementation = Response.class))),
			
			@ApiResponse( responseCode = "400", 
			description = "login failed ",
			content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = Response.class)
		))
	})
	 // localhost:8080/user/login
	@PutMapping("login")
	public ResponseEntity<Response> login(@RequestBody Map<String, String> reqBody) {
		Response response = profileService.login(reqBody.get("username"), reqBody.get("password"));
		//TODO: process PUT request
		
		return ResponseEntity.status(response.getCodice()).body(response);
	}

}
