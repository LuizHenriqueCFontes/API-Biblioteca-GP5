package com.biblioteca.gp5.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.exception.storage.FailedCreateDirectoryException;
import com.biblioteca.gp5.exception.storage.FailedSaveFileException;

@Service
public class LocalStorageService implements StorageService {
	
	private static final String BOOKS_DIRECTORY = "books";
	private static final String COVERS_DIRECTORY = "covers";
	
	private static final String BOOKS_EXTESION = "epub";
	private static final String COVERS_EXTESION = "jpeg";
	
	private final Path rootPath;
	
	public LocalStorageService(StorageProperties properties) {
		this.rootPath = Paths.get(properties.path());
		
		initializeDirectories();
	}
	
	public void initializeDirectories() {
		
		try {
			Files.createDirectories(rootPath.resolve(BOOKS_DIRECTORY));
			
			Files.createDirectories(rootPath.resolve(COVERS_DIRECTORY));
			
		} catch(IOException ex) {
			throw new FailedCreateDirectoryException("Falha ao criar diretorios", ex);
			
		}
	}
	
	@Override
	public String saveEpub(byte[] content) {
		String directoryBook = save(content, BOOKS_DIRECTORY, BOOKS_EXTESION);
		
		return directoryBook;
	}
	
	@Override
	public String saveCover(byte[] content) {
		String coverDirectory = save(content, COVERS_DIRECTORY, COVERS_EXTESION);
		
		return coverDirectory;
	}
	
	public String save(byte[] content, String directory, String extesion) {
		
		try {
			String fileName = UUID.randomUUID() + "." + extesion;
			
			Path destination = rootPath.resolve(directory)
										.resolve(fileName);
			
			Files.write(destination, content);
			
			return directory + "/" + fileName;
			
		} catch(IOException ex) {
			throw new FailedSaveFileException("Falha ao salvar arquivo", ex);
			
		}
		
	}
	
	

}
