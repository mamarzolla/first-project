package it.corso.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")  // consenti tutte le rotte
	                .allowedOrigins("http://localhost:4200")  // consenti l'origine della tua app Angular
	                .allowedMethods("GET", "POST", "PUT", "DELETE")  // metodi consentiti
	                .allowedHeaders("*")
	                .allowCredentials(true);  // se hai bisogno di inviare credenziali come cookie
	    }
}
