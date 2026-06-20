package com.biblioteca.gp5.storage;

public interface StorageService {
	
	String saveEpub(byte[] content);
	
	String saveCover(byte[] content);
}
