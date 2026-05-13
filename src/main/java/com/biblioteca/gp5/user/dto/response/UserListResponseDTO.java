package com.biblioteca.gp5.user.dto.response;

import com.biblioteca.gp5.user.model.UserRole;

public record UserListResponseDTO(String username, String email, String phone, UserRole role) {

}
