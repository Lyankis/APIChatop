package com.openclassrooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })//(exclude = {UserDetailsServiceAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "Hello World", version = "1.0.0", description = "Test", termsOfService = "runcodenow", contact = @Contact(name = "Nicolas", email = "n@gmail.com"), license = @License(name = "licence", url = "runcodenow")))
public class ApiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTestApplication.class, args);
	}

}