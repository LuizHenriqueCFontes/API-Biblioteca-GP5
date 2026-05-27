package com.biblioteca.gp5.book.enums;

import lombok.Getter;

@Getter
public enum BookCover {
	IMAGE("image/jpeg");
	
	private final String value;
	
	BookCover(String value) {
		this.value = value;
	}

}
