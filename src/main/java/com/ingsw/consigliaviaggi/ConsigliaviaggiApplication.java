package com.ingsw.consigliaviaggi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ConsigliaviaggiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsigliaviaggiApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/admin/aggiungistruttura").allowedOrigins("http://localhost:3000");
			}
		};
	}

}




