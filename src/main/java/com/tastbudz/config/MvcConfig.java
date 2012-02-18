package com.tastbudz.config;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

@Configuration
@Import({AppConfig.class})
@ComponentScan("com.tastbudz.controller")
@ImportResource("classpath:/com/tastbudz/spring-mvc.xml")
public class MvcConfig {
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
	
	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().setAnnotationIntrospector(getAnnotationIntrospector());
		mapper.getDeserializationConfig().setAnnotationIntrospector(getAnnotationIntrospector());
		mapper.getSerializationConfig().enable(SerializationConfig.Feature.INDENT_OUTPUT);
		return mapper;
	}
}
