package it.corso.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component 
public class TokenGenerator {
	
	public String generateToken(String nickname) {
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.toInstant(OffsetDateTime.now().getOffset());
		Long timestamp = instant.getEpochSecond()*1000;
		String nicknameTk = Base64.getEncoder().encodeToString(nickname.getBytes());
		
		return nicknameTk + "_"+ timestamp;  
	}

}
