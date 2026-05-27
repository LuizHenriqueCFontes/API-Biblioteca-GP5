package com.biblioteca.gp5.integration.gutendex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
	
	@Bean
	RestClient restClientGutendex(GutendexProperties properties) {
		
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		
		factory.setConnectTimeout(5000);
		factory.setReadTimeout(40000);
		
		return RestClient.builder()
						.baseUrl(properties.getBaseUrl())
						.requestFactory(factory)
						.build();
	}
	
	

}
