package com.biblioteca.gp5.integration.gutendex.dto.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GutendexBookResponseDTO(
		Integer id,
		String title,
		List<GutendexAuthorResponseDTO> authors,
		List<String> summaries,
		List<String> bookshelves,
		
		@JsonProperty("media_type")
		String mediaType,
		
		Map<String, String> formats,
		
		@JsonProperty("download_count")
		Integer downloadCount
		
		) {

}
