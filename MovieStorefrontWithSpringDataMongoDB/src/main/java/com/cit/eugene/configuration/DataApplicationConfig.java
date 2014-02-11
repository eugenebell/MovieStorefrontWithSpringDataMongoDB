package com.cit.eugene.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@PropertySource("classpath:/com/cit/eugene/configuration/datasource.properties")
@EnableMongoRepositories("com.cit.eugene.service.dao")
@EnableTransactionManagement
@Import({/*ApplicationConfig.class,*/ SecurityApplicationConfig.class})
public class DataApplicationConfig extends AbstractMongoConfiguration {

    private Environment environment;
    
	@Autowired	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(environment.getProperty("datasource.driverClassName"));
		ds.setUrl(environment.getProperty("datasource.url"));
		ds.setUsername(environment.getProperty("datasource.username"));
		ds.setPassword(environment.getProperty("datasource.password"));
		return ds;
	}
	
//	@Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(jpaVendorAdapter());
//        factory.setDataSource(dataSource());
//        factory.setPersistenceUnitName("video-unit");
//       return factory;
//    }

//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//    	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(Boolean.FALSE);
//        vendorAdapter.setShowSql(Boolean.TRUE);
//        vendorAdapter.setDatabase(Database.MYSQL);
//        return vendorAdapter;
//    }

	@Override
	protected String getDatabaseName() {
		return environment.getProperty("mongodb.db");
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(environment.getProperty("mongodb.host"), Integer.valueOf(environment.getProperty("mongodb.port")));
	}


//    @Value("classpath:mysql-schema.sql")
//    private Resource schemaScript;
//
//    @Value("classpath:sample-setup-data.sql")
//    private Resource dataScript;

//    @Bean
//    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
//        final DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//        initializer.setDatabasePopulator(databasePopulator());
//        return initializer;
//    }

//    private DatabasePopulator databasePopulator() {
//        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(schemaScript);
//        populator.addScript(dataScript);
//        return populator;
//    }
    
}
