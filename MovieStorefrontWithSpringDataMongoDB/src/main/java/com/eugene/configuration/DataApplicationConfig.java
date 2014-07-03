package com.eugene.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@Profile("default")
@PropertySource("classpath:mongodb.properties")
@EnableMongoRepositories("com.eugene.service.dao")
@EnableTransactionManagement
@Import({ ApplicationConfig.class, SecurityApplicationConfig.class })
public class DataApplicationConfig {// extends AbstractMongoConfiguration {

	private Environment environment;

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), environment.getProperty("mongodb.db"));
	}

	@Bean
	public Mongo mongo() throws Exception {
		MongoClientURI mci = new MongoClientURI(environment.getProperty("mongodb.url"));
		return new MongoClient(mci);
	}

}
