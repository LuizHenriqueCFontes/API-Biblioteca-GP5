package com.biblioteca.gp5.book.dto.response;

import java.util.List;

public record ImportBookResponseDTO(Integer id, String title, String authors, String description, String coverUrl, String fileUrl) {

}
