package com.wp.Pets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @EntityScan makes Spring to scan for entities in the folder specified
@EntityScan("com.wp.Pets.models")
// @ComponentScan makes Spring to scan for beans in the folder specified
@ComponentScan("com.wp.Pets")
public class PetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsApplication.class, args);
	}

}
