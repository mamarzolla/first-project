package it.corso.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

// localhost:8080/swagger-ui.html

@Configuration
public class BeanHandler {
	
	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}
	@Bean   ///oggetto gestito dal container 
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	OpenAPI apiDocumentation () {
		
		OpenAPI api = new OpenAPI();
		Info apiInfo = new Info();
		// documentazione ufficiale 
		apiInfo.title("labair Web Service");
		apiInfo.description("restfull Web service App Labair");
		apiInfo.version("1.0.0.0");
		api.info(apiInfo);
		return api;
	}

}
