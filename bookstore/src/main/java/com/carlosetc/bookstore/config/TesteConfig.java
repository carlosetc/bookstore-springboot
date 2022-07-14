package com.carlosetc.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carlosetc.bookstore.services.DBService;

@Configuration
@Profile("teste")
public class TesteConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciarDatabase() {
		this.dbService.instanciarDatabase();
	}
	
}
