package com.biblioteca.gp5.user.mapper;


import org.mapstruct.Mapper;
import com.biblioteca.gp5.user.dto.response.ListResponseDTO;
import com.biblioteca.gp5.user.model.Users;

//Mapper utilizado pelo MapStruct para converter entidades em DTOs automaticamente.
//O componentModel = "spring" registra o mapper como um bean do Spring,
//permitindo injeção de dependência normalmente.
@Mapper(componentModel = "spring")
public interface UserMapper {
	
	ListResponseDTO toListResponseDTO(Users users);

}
