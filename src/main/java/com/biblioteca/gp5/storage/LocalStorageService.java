package com.biblioteca.gp5.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.exception.storage.FailedCreateDirectoryException;

@Service
public class LocalStorageService implements LocalStorageService {
	
	private static final String BOOKS_DIRECTORY = "books";
	private static final String COVER_DIRECTORY = "covers";
	
	private final Path rootPath;
	
	public LocalStorageService(StorageProperties properties) {
		this.rootPath = Paths.get(properties.path());
		
		initializeDirectories();
	}
	
	public void initializeDirectories() {
		
		try {
			Files.createDirectories(rootPath.resolve(BOOKS_DIRECTORY));
			
			Files.createDirectories(rootPath.resolve(COVER_DIRECTORY));
			
		} catch(IOException ex) {
			throw new FailedCreateDirectoryException("Falha ao criar diretorios", ex);
			
		}
	}
	
	public String save(byte[] content, String directory, String extesion) {
		
	}
	
	

}
