package com.biblioteca.gp5.book.enums;

import lombok.Getter;

@Getter
public enum BookFormat {
	
	EPUB("application/epub+zip");
	
	private final String value;
		
	BookFormat(String value){
		this.value = value;
	}
}
