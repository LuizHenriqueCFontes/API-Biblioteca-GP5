package com.biblioteca.gp5.integration.gutendex.download;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class FileDownloadConfig {
	
	@Bean
	RestClient fileDownloadRestClient() {
		HttpClient httpClient = HttpClient.newBuilder()
										  .followRedirects(HttpClient.Redirect.NORMAL)
										  .build();
		
		ClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
		
		return RestClient.builder()
						 .requestFactory(requestFactory)
						 .build();
	}

}
