package com.biblioteca.gp5.book.dto.response;

import java.util.List;

public record ImportBookDetailsResponseDTO(Integer id, String title, List<String> authors, List<String> description, List<String> bookShelves, String coverUrl, String fileUrl) {

}
