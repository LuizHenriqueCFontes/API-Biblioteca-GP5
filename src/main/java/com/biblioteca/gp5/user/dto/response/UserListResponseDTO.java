package com.biblioteca.gp5.user.dto.response;

import java.util.UUID;

import com.biblioteca.gp5.user.model.UserRole;

public record UserListResponseDTO(UUID idUser, String username, String email, String phone, UserRole role) {

}
