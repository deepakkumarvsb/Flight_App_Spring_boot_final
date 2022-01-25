package com.flightapp.UserService.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AirlineServiceConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
	

}
