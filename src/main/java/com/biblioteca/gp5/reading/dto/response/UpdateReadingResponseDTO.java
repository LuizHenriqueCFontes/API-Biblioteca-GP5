package com.biblioteca.gp5.reading.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateReadingResponseDTO(String epubCfi, BigDecimal percentage, LocalDateTime lastReading) {

}
