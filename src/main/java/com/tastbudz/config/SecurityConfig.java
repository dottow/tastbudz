package com.tastbudz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@ImportResource("classpath:com/tastbudz/security.xml")
public class SecurityConfig {
	private final static String SECRET = "3n+R0pY";
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder(SECRET);
	}
}
