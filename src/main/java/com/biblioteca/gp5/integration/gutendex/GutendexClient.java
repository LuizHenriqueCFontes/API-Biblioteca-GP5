package com.biblioteca.gp5.integration.gutendex;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Configuration
@ConfigurationProperties(prefix = "gutendex")
@Data
public class GutendexClient {
	
	private String baseUrl = "http://gutendex.com";

}
