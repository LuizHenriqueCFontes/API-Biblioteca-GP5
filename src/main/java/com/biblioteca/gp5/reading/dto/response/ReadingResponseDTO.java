package com.biblioteca.gp5.reading.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReadingResponseDTO(UUID idBook, String epubCfi, BigDecimal percentage, LocalDateTime lastReading) {

}
