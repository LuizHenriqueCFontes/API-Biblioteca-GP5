package com.biblioteca.gp5.integration.gutendex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
	
	@Bean
	RestClient restClientGutendex(GutendexProperties properties) {
		
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		
		factory.setConnectTimeout(5000);
		factory.setReadTimeout(100000);
		
		return RestClient.builder()
						.baseUrl(properties.getBaseUrl())
						.defaultHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0 Safari/537.36")
						.requestFactory(factory)
						.build();
	}
	
	

}
