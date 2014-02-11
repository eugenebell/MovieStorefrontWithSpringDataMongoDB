package com.cit.eugene.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//TODO http://www.luckyryan.com/2013/02/07/migrate-spring-mvc-servlet-xml-to-java-config/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.cit.eugene.web"})
public class WebApplicationConfig extends WebMvcConfigurerAdapter  {
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
	
	//http://zeroturnaround.com/rebellabs/your-next-java-web-app-less-xml-no-long-restarts-fewer-hassles-part-2/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//	 OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
//	 interceptor.setEntityManagerFactory(entityManagerFactory.getObject());
//	 registry.addWebRequestInterceptor(interceptor);
	}
	
	@Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
