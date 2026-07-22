package com.biblioteca.gp5.book.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record BooksRecentlyCreatedResponseDTO(String coverUrl, String title, List<String> authors, LocalDateTime creationDate) {

}
