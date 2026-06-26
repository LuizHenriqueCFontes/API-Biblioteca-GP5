package com.biblioteca.gp5.integration.gutendex.download;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FileDownloadService {
	
	private final RestClient restClient;
	
	public FileDownloadService(@Qualifier("fileDownloadRestClient") RestClient restClient) {
		this.restClient = restClient;
	}
	
	public byte[] download(String url) {
		return restClient.get()
						 .uri(url)
						 .retrieve()
						 .body(byte[].class);
	}

}
