package com.biblioteca.gp5.integration.gutendex.dto.response;

import java.util.List;

public record GutendexSearchResponseDTO(Integer count, String next, String previous, List<GutendexSearchResponseDTO> results) {

}
