package com.biblioteca.gp5.book.service;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.integration.gutendex.client.GutendexClient;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;

@Service
public class BookManagementService {
	
	private final GutendexClient gutendexClient;
	
	public BookManagementService(GutendexClient gutendexClient) {
		this.gutendexClient = gutendexClient;
	}
	
	public GutendexSearchResponseDTO gutendexSearchBooks(String title) {
		return gutendexClient.searchBooks(title);
		
	}
	
}
