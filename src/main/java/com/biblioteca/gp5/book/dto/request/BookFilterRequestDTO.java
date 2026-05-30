package com.biblioteca.gp5.book.dto.request;

import java.util.List;
import java.util.UUID;

public record BookFilterRequestDTO(String title, List<UUID> idCategory) {

}
