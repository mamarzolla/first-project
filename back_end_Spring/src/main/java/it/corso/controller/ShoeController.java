package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.dto.InsertShoe;
import it.corso.dto.ListShoeDto;
import it.corso.dto.ShoeDto;
import it.corso.helper.Response;
import it.corso.model.Shoe;
import it.corso.service.ShoeService;



@RestController
@RequestMapping("/shoe")   // localhost:8080/shoe
@Tag(name="ShoeController",description = "shoe control functionality")
public class ShoeController {
	
	@Autowired
	private ShoeService shoeService;
	// Endpoint 1 localhost:8080/shoe/list
	
	@Operation
	(
			summary = "shoe list",
			description =  "shoe list request management"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json"
			//schema = @Schema(implementation = classe)
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "200", 
							description = "Return shoes list",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					)
			}
			)
	@GetMapping("/list")
	public ResponseEntity<List<ListShoeDto>>  findAllShoes() {
		return ResponseEntity.status(HttpStatus.OK).body(shoeService.shoesList());
	}
	
	@Operation
	(
			summary = "shoe",
			description =  "shoe id request management"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json"
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "200", 
							description = "Return single shoe",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					),
					@ApiResponse( responseCode = "404", 
					description = "id not found",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
			)
			}
			)
	
	// Endpoint 2 localhost:8080/shoe/find/{id}
	
	@GetMapping ("find/{id}")
	public ResponseEntity<Object> singleShoe(@Parameter(description = "Shoe id ")@PathVariable ("id") Integer id) {
		Object resp = shoeService.findShoeById(id); // prima si assegna all'object per poi castare successivamente quando arriva il risultato
		if (resp instanceof Response)
			return ResponseEntity.status(((Response)resp).getCodice()).body((Response)resp);
		return ResponseEntity.status(HttpStatus.OK).body((ShoeDto)resp); // con l'option potremmo avere la risposta che è istanza o di scarpa o di risposta quindi il downcasting va di conseguenza
		
	}
	
	@Operation
	(
			summary = " add shoe",
			description =  "insert new shoe"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = ShoeDto.class)
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "201", 
							description = "shoe added correctly",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					),
					@ApiResponse( responseCode = "404", 
					description = "property not valid or not found",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
					),
					@ApiResponse( responseCode = "400", 
					description = "data integrity violation or id shoe present in body request",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
					)
			}
			)
	// Endpoint 3   --  localhost:8080/shoe/create
	
	@PostMapping("/create")
	public ResponseEntity<Response> setShoe(  @RequestBody Shoe shoe ) {	//@Valid
		Response resp = shoeService.setNewShoe(shoe);
		return ResponseEntity.status(resp.getCodice()).body(resp);
	}
	
	
	@Operation
	(
			summary = "shoe",
			description =  "shoe id request management"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json"
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "202", 
							description = "shoe deleted",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					),
					@ApiResponse( responseCode = "404", 
					description = "id not found",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
			)
			}
			)
	
	
	// Endpoint 5   --  localhost:8080/shoe/delete/{id}
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<Response> deleteShoe(@Parameter(description = "Shoe id ")@PathVariable("id") Integer id) {
		Response resp =shoeService.deleteShoe(id);
		return ResponseEntity.status(resp.getCodice()).body(resp);
	}
	@Operation
	(
			summary = " modify shoe",
			description =  "modify existing shoe finded by id"
			)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content(
			mediaType = "application/json",
			schema = @Schema(implementation = ShoeDto.class)
			)
	)@ApiResponses(
			value = {
					@ApiResponse( responseCode = "201", 
							description = "Shoe modified correctly",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Response.class)
									)
					),
					@ApiResponse( responseCode = "404", 
					description = "property not valid or not found",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Response.class)
							)
					)
			}
			)
	//localhost:8080/shoe/mod{id}
	
	@PutMapping("/mod/{id}")
	public ResponseEntity<Response> modifyShoe(@Parameter(description = "Shoe id ")@PathVariable Integer id, @RequestBody Shoe newShoe){
		Response response = shoeService.modShoe(id, newShoe);
		return ResponseEntity.status(response.getCodice()).body(response);
	}
	
	@PostMapping("/createList")
	public ResponseEntity<Response> setShoeLi( @RequestBody List<InsertShoe> shoe ) {	
		Response resp = shoeService.setListShoes(shoe);
		return ResponseEntity.status(resp.getCodice()).body(resp);
	}
	
	@PostMapping(value = "/{id}/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> uploadImage(@PathVariable Integer id,
                                                 @RequestPart(value = "image") MultipartFile image) {
        Response response = shoeService.addImageToShoe(id, image);
        return ResponseEntity.status(response.getCodice()).body(response);
    }
	
	@GetMapping("downloadImg/{id}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
        byte[] imageBytes;
        	imageBytes= shoeService.getImgbyShoeId(id);
        
        // Imposta i metadati della risposta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Cambia in base al formato dell'immagine
        headers.setContentLength(imageBytes.length);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
	
}
