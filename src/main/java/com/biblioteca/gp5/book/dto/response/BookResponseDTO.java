package com.biblioteca.gp5.book.dto.response;

import java.util.List;
import java.util.UUID;

public record BookResponseDTO(UUID id, String title, List<String> authors, String coverUrl, String fileUrl) {

}
