package com.biblioteca.gp5.reading.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.reading.dto.response.ReadingResponseDTO;
import com.biblioteca.gp5.reading.dto.response.UpdateReadingResponseDTO;
import com.biblioteca.gp5.reading.model.Reading;
import com.biblioteca.gp5.shared.mapper.FileUrlMapper;

@Mapper(componentModel = "spring", uses = FileUrlMapper.class)
public interface ReadingMapper {
	
	@Mapping(source = "reading.book.idBook", target = "idBook")
	@Mapping(source = "reading.book.fileUrl", target = "fileUrl", qualifiedByName = "toFileUrl")
	ReadingResponseDTO toReadingResponseDTO(Reading reading);
	
	UpdateReadingResponseDTO toReadingUpdateResponseDTO(Reading reading);

}
