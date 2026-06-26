package com.biblioteca.gp5.reading.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.reading.dto.request.UpdateReadingRequestDTO;
import com.biblioteca.gp5.reading.dto.response.ReadingResponseDTO;
import com.biblioteca.gp5.reading.dto.response.UpdateReadingResponseDTO;
import com.biblioteca.gp5.reading.service.ReadingService;
import com.biblioteca.gp5.user.model.User;

import jakarta.validation.Valid;

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
	
	@PatchMapping("/{idBook}")
	public ResponseEntity<UpdateReadingResponseDTO> updateReading(@AuthenticationPrincipal User user, @PathVariable UUID idBook,
																@RequestBody @Valid UpdateReadingRequestDTO request) {
		UpdateReadingResponseDTO response = readingService.updateReading(user.getIdUsers(), idBook, request);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{idBook}")
	public ResponseEntity<ReadingResponseDTO> getReadingProgress(@AuthenticationPrincipal User user, @PathVariable UUID idBook) {
		ReadingResponseDTO response = readingService.getReadingProgress(user.getIdUsers(), idBook);
		
		return ResponseEntity.ok(response);
	}

}
