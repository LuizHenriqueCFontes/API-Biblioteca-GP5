package com.biblioteca.gp5.book.dto.response;

import java.util.List;

public record ImportSearchResponseDTO(Integer count, String next, String previous, List<ImportBookResponseDTO> results) {

}
