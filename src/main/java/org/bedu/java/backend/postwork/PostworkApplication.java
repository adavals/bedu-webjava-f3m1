package org.bedu.java.backend.postwork;

import jakarta.persistence.Column;
import org.bedu.java.backend.postwork.persistence.ClienteRepository;
import org.bedu.java.backend.postwork.persistence.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PostworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostworkApplication.class, args);
	}

}
