package com.example.santanderavaliacaospring;

import com.example.santanderavaliacaospring.models.Resistance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SantanderAvaliacaoSpringApplication {
	public static Resistance resistance = new Resistance();
	public static void main(String[] args) {
		SpringApplication.run(SantanderAvaliacaoSpringApplication.class, args);
	}

}
