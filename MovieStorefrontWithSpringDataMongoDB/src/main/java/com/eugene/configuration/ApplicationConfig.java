package com.eugene.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.eugene.init.InitializeMongoDB;

@Configuration
@ComponentScan("com.eugene")
public class ApplicationConfig {

	@Bean
	public InitializeMongoDB initMongoDB(){
		return new InitializeMongoDB();
	}
}
