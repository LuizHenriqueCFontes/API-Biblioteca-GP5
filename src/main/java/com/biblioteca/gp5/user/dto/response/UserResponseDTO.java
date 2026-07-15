package com.biblioteca.gp5.user.dto.response;

import java.util.UUID;

public record UserResponseDTO(UUID idUser, String username, String email, String phone) {

}
