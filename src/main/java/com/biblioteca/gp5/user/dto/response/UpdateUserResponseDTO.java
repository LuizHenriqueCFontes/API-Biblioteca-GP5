package com.biblioteca.gp5.user.dto.response;

import java.util.UUID;

public record UpdateUserResponseDTO(UUID id, String username, String email, String phone) {

}
