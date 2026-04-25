package com.biblioteca.gp5.integration.gutendex;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;

@Component
public class GutendexClient {
	
	private final RestClient.Builder builder;
	private final GutendexProperties properties;
	
	public GutendexClient(RestClient.Builder builder, GutendexProperties properties) {
		this.builder = builder;
		this.properties = properties;
	}
	
	public GutendexSearchResponseDTO searchBooks(String title) {
		RestClient client = builder.baseUrl(properties.getBaseUrl())
									.build();
		
		return client.get()
					.uri(uri -> uri
								.path("/books")
								.queryParam("search", title)
								.build())
					.retrieve()
					.body(GutendexSearchResponseDTO.class);
		
	}
}
