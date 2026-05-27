package com.biblioteca.gp5.integration.gutendex.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.biblioteca.gp5.integration.gutendex.config.RestClientConfiguration;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexBookResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;

@Component
public class GutendexClient {
	
	private final RestClient restClient;
	
	public GutendexClient(RestClient restClient) {
		this.restClient = restClient;
	}
	
	public GutendexSearchResponseDTO searchBooks(String title, Integer page) {
		
		return restClient.get()
						.uri(uriBuilder -> {
							
							uriBuilder.path("/books/");
							
							if(title != null && !title.isBlank()) {
								uriBuilder.queryParam("search", title);
							}
							
							if(page != null) {
								uriBuilder.queryParam("page", page);
							}
							
							return uriBuilder.build();
							
						})
						.retrieve()
						.body(GutendexSearchResponseDTO.class);
	}
	
	public GutendexBookResponseDTO searchBookById(Integer id) {
		return restClient.get()
						.uri("/books/{id}/", id)
						.retrieve()
						.body(GutendexBookResponseDTO.class);
	}
	
	
}
