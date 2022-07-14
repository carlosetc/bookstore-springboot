package com.carlosetc.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carlosetc.bookstore.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	@Bean
	public void instanciarDatabase() {
		if("create".equals(ddlAuto)) {
			this.dbService.instanciarDatabase();
		}
	}
	
}
