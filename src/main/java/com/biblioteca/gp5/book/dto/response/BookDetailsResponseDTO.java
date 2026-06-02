package com.biblioteca.gp5.book.dto.response;

import java.util.List;
import java.util.UUID;

public record BookDetailsResponseDTO(UUID id, String title, List<String> authors, List<String> description, String coverUrl, String fileUrl) {

}
