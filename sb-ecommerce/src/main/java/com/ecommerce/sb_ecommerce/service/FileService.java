package com.ecommerce.sb_ecommerce.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadImage(String path, MultipartFile image) throws IOException;
	
}
