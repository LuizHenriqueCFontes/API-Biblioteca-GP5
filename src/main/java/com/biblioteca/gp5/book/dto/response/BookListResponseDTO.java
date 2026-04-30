package com.biblioteca.gp5.book.dto.response;

import java.util.List;

public record BookListResponseDTO(String id, String tilte, List<String> authors, String readLink) {

}
