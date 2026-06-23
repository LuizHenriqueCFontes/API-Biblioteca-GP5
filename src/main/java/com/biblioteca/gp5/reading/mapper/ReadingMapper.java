package com.biblioteca.gp5.reading.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.reading.dto.response.ReadingResponseDTO;
import com.biblioteca.gp5.reading.dto.response.UpdateReadingResponseDTO;
import com.biblioteca.gp5.reading.model.Reading;

@Mapper(componentModel = "spring")
public interface ReadingMapper {
	
	@Mapping(source = "reading.book.idBook", target = "idBook")
	ReadingResponseDTO toReadingResponseDTO(Reading reading);
	
	UpdateReadingResponseDTO toReadingUpdateResponseDTO(Reading reading);

}
