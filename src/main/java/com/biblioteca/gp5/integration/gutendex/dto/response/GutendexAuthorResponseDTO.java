package com.biblioteca.gp5.integration.gutendex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GutendexAuthorResponseDTO(
		
		String name,
		
		@JsonProperty("birth_year")
		Integer birthYear,
		
		@JsonProperty("death_year")
		Integer deathYear
		) {

}
