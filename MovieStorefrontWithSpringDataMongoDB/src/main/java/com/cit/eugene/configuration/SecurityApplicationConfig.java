package com.cit.eugene.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//http://spring.io/blog/2013/07/04/spring-security-java-config-preview-method-security
//http://docs.spring.io/autorepo/docs/spring-security/3.2.0.RC2/reference/htmlsingle/#abstractsecuritywebapplicationinitializer-with-spring-mvc
@Configuration
@EnableWebSecurity
//securedEnabled=true Enables Spring security, others include JSR250 and prePost
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityApplicationConfig extends WebSecurityConfigurerAdapter {

//	private DataSource dataSource;
	
//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//        .dataSource(dataSource)
//        .usersByUsernameQuery("select USERNAME, PASSWORD, ENABLED from USERS where USERNAME = ?")
//        .authoritiesByUsernameQuery("select USERNAME, AUTHORITY from AUTHORITIES where USERNAME = ?");
//    }
	
	 @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/images/**")
	         .antMatchers("/scripts/**")
	         .antMatchers("/styles/**");
	  }
	 
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/index.html","/denied.htm","/favicon.ico").permitAll() 
	        .antMatchers("/movies/movieListing/**").hasRole("USER")
	        .antMatchers("/movies/createVideoStoremember*").hasRole("ADMIN")
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/movies/login")
	            .loginProcessingUrl("/j_spring_security_check")
	            .usernameParameter("username")
	            .passwordParameter("password")
	            .failureUrl("/j_spring_security_check?error")
	            .permitAll()
	            .and()
	        .logout()
	        	.logoutUrl("/j_spring_security_logout")
	        	.logoutSuccessUrl("/index.html");
	  }
	
}
