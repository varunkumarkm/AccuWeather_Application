package com.AccuWeather.Application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager CreateUserDetailsManager() {
	
		
		UserDetails userdetails = User.withDefaultPasswordEncoder()
				.username("test")
				.password("test")
				.roles("USER", "ADMIN")
				.build();
		return new InMemoryUserDetailsManager(userdetails);
	}	
}
