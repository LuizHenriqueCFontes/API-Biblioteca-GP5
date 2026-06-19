package com.biblioteca.gp5.storage;

public interface StorageService {
	
	String saveBook(byte[] cotent);
	
	String saveCover(byte[] content);
}
