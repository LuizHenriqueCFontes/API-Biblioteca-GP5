package com.biblioteca.gp5.reading.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.reading.dto.ReadingResponseDTO;
import com.biblioteca.gp5.reading.service.ReadingService;
import com.biblioteca.gp5.user.model.User;

@RestController
@RequestMapping("/api/reading")
public class ReadingController {
	
	private final ReadingService readingService;
	
	public ReadingController(ReadingService readingService) {
		this.readingService = readingService;
	}
	
	@PostMapping("/{idBook}")
	public ResponseEntity<ReadingResponseDTO> startReading(@AuthenticationPrincipal User user, @PathVariable UUID idBook) {
		ReadingResponseDTO response = readingService.startReading(user.getIdUsers(), idBook);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
