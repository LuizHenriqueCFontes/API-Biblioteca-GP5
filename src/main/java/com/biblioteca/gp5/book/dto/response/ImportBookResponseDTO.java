package com.biblioteca.gp5.book.dto.response;

import java.util.List;

public record ImportBookResponseDTO(Integer id, String title, List<String> authors, List<String> description, String coverUrl, String fileUrl) {

}
