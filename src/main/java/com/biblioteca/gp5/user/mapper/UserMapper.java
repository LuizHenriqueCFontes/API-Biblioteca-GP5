package com.biblioteca.gp5.user.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.user.dto.response.UserListResponseDTO;
import com.biblioteca.gp5.user.model.User;

//Mapper utilizado pelo MapStruct para converter entidades em DTOs automaticamente.
//O componentModel = "spring" registra o mapper como um bean do Spring,
//permitindo injeção de dependência normalmente.
@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(source = "idUsers", target = "idUser")
	UserListResponseDTO toUserListResponseDTO(User users);

}
