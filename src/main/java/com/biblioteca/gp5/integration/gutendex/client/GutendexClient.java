package com.biblioteca.gp5.integration.gutendex.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;

@Component
public class GutendexClient {
	
	private final RestClient restClient;
	
	public GutendexClient(RestClient restClient) {
		this.restClient = restClient;
	}
	
	public GutendexSearchResponseDTO searchBooks(String title) {
		return restClient.get()
						.uri(uri -> {
							
							uri.path("/books");
							
							if(title != null && !title.isBlank()) {
								uri.queryParam("search", title);
							}
							
							return uri.build();
						})
						.retrieve()
						.body(GutendexSearchResponseDTO.class);
						
	}
}
