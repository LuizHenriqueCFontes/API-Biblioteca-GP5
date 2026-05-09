package com.biblioteca.gp5.book.enums;

import lombok.Getter;

@Getter
public enum BookSources {
	GUTENDEX("GUTENDEX");
	
	String value;
	
	private BookSources(String value) {
		this.value = value;
	}

}	
