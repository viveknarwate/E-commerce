package com.ecommerce.sb_ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{
	
	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String originalFileName = file.getOriginalFilename();
		//generate a unique file name
		String randomId = UUID.randomUUID().toString();
		//mat.jpg ---124--1234.jpg
		String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
		String filePath = path + File.separator + fileName;
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName;
	}

}
