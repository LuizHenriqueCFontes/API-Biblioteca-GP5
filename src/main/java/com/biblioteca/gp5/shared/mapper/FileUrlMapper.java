package com.biblioteca.gp5.shared.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class FileUrlMapper {
	
	@Value("${app.files.base-url}")
	private String filesBaseUrl;
	
	@Named("toFileUrl")
	public String toFileUrl(String path) {
		
		if(path == null || path.isBlank()) {
			return null;
		}
		
		return filesBaseUrl + "/" + path;
	}

}
