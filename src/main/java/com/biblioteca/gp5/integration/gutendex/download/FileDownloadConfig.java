package com.biblioteca.gp5.integration.gutendex.download;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FileDownloadConfig {
	
	@Bean
	RestClient fileDownloadRestClient() {
		return RestClient.builder().build();
	}

}
