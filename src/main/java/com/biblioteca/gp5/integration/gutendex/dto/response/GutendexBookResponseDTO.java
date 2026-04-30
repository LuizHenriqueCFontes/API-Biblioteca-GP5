package com.biblioteca.gp5.integration.gutendex.dto.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GutendexBookResponseDTO(
		Long id,
		String title,
		List<GutendexAuthorResponseDTO> authors,
		List<String> subjects,
		List<String> summaries,
		List<GutendexAuthorResponseDTO> translators,
		List<String> languages,
		boolean copyright,
		
		@JsonProperty("media_type")
		String mediaType,
		
		Map<String, String> formats,
		
		@JsonProperty("download_count")
		Integer downloadCount
		
		) {

}
