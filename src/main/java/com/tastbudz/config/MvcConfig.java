package com.tastbudz.config;

import java.util.List;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import com.tastbudz.json.JsonHttpMessageConverter;

@Import({AppConfig.class})
@ComponentScan("com.tastbudz.controller")
@ImportResource("classpath:/com/tastbudz/spring-mvc.xml")
@EnableWebMvc
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new JsonHttpMessageConverter());
	}
	  
	@Bean
	public HandlerMapping getHandlerMapping() {
		return new DefaultAnnotationHandlerMapping();
	}
	
	@Bean
	public HandlerAdapter getHandlerAdapter() {
		return new AnnotationMethodHandlerAdapter();
	}

	@Bean
	public AnnotationIntrospector getAnnotationIntrospector() {
		return new JaxbAnnotationIntrospector();
	}	
}
