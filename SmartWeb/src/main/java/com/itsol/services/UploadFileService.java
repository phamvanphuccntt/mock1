package com.itsol.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	private String UPLOAD_CV = System.getProperty("user.dir") + "/cv";
	private String UPLOAD_DIR = System.getProperty("user.dir") + "/images";
	private String UPLOAD_DIR_SLIDER = System.getProperty("user.dir") + "/imagesSlider";

	// Upload File
	public String uploadFile(MultipartFile file) throws IOException {
		// Make sure directory exists!
		File uploadDir = new File(UPLOAD_DIR);
		uploadDir.mkdirs();
		if (file.isEmpty()) {
			return "empty";
		}
		String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(uploadFilePath);
		Files.write(path, bytes);
		return file.getOriginalFilename();
	}

	// Upload CV
	public String uploadCV(MultipartFile file) throws IOException {
		// Make sure directory exists!
		File uploadDir = new File(UPLOAD_CV);
		uploadDir.mkdirs();
		if (file.isEmpty()) {
			return "empty";
		}
		String uploadFilePath = UPLOAD_CV + "/" + file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(uploadFilePath);
		Files.write(path, bytes);
		return file.getOriginalFilename();
	}

	// up load file for slider
	public String uploadFileSlider(MultipartFile file) throws IOException {
		// Make sure directory exists!
		File uploadDir = new File(UPLOAD_DIR_SLIDER);
		uploadDir.mkdirs();
		if (file.isEmpty()) {
			return "empty";
		}
		String uploadFilePath = UPLOAD_DIR_SLIDER + "/" + file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(uploadFilePath);
		Files.write(path, bytes);
		return file.getOriginalFilename();

	}

	// Get image
	public Resource getImage(String filename) throws MalformedURLException {
		File file = new File(UPLOAD_DIR + "/" + filename);
		return new UrlResource(file.toURI());
	}

	public Resource downloadCV(String filename) throws MalformedURLException {
		File file = new File(UPLOAD_CV + "/" + filename);
		return new UrlResource(file.toURI());
	}

	/// GET IMAGE SLIDER
	public Resource getImageSlider(String filename) throws MalformedURLException {
		File file = new File(UPLOAD_DIR_SLIDER + "/" + filename);
		return new UrlResource(file.toURI());
	}

}
