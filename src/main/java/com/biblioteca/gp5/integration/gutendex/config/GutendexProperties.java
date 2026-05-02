package com.biblioteca.gp5.integration.gutendex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "gutendex")
@Getter
@Setter
public class GutendexProperties {
	
	private String baseUrl;

}
