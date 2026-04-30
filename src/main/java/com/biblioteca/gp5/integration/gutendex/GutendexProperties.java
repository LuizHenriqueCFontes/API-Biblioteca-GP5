package com.biblioteca.gp5.integration.gutendex;

import org.springframework.boot.context.properties.ConfigurationProperties;


import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "gutendex")
@Getter
@Setter
public class GutendexProperties {
	
	private String baseUrl;

}
