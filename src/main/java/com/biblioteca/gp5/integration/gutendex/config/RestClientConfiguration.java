package com.biblioteca.gp5.integration.gutendex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
	
	@Bean
	RestClient restClient(GutendexProperties properties) {
		return RestClient.builder()
						.baseUrl(properties.getBaseUrl())
						.build();
	}
	
	

}
