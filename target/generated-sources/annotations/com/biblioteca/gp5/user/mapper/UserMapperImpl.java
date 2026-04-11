package com.biblioteca.gp5.user.mapper;

import com.biblioteca.gp5.user.dto.response.ListResponseDTO;
import com.biblioteca.gp5.user.model.UserRole;
import com.biblioteca.gp5.user.model.Users;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-10T20:56:14-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ListResponseDTO toListResponseDTO(Users users) {
        if ( users == null ) {
            return null;
        }

        String username = null;
        String email = null;
        String phone = null;
        UserRole role = null;

        username = users.getUsername();
        email = users.getEmail();
        phone = users.getPhone();
        role = users.getRole();

        ListResponseDTO listResponseDTO = new ListResponseDTO( username, email, phone, role );

        return listResponseDTO;
    }
}
