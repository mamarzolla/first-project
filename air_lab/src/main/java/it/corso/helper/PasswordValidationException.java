package it.corso.helper;

public class PasswordValidationException extends RuntimeException {

	private static final long serialVersionUID = 5054532917036415626L;
	
	private final String message ="password not valid";
	
	public String getMessage() {
		
		return message;
		
	}
	
	
	

}
