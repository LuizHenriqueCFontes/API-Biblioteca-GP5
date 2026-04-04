package com.biblioteca.gp5.exception.dto;

import java.time.LocalDateTime;


//Crio um record padrao dos erros de exception
public record ErrorResponse(LocalDateTime timestamp, Integer status, String error, String message) {

}
