package com.itsol.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itsol.services.UploadFileService;

@RestController
public class UploadFileController {

	@Autowired
	UploadFileService uploadFileService;

	@RequestMapping(value = "images/{filename}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public Resource getImage(@PathVariable String filename) throws MalformedURLException {
		return uploadFileService.getImage(filename);
	}

	@RequestMapping(value = "images", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_HR') or hasRole('ROLE_MANAGER')")
	public String uploadFile(@RequestBody MultipartFile file) throws IOException {
		return uploadFileService.uploadFile(file);
	}

	@RequestMapping(value = "imageSlider", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_MANAGER')")
	public String uploadFileSlider(@RequestBody MultipartFile file) throws IOException {
		return uploadFileService.uploadFileSlider(file);

	}

	@RequestMapping(value = "imagesSlider/{filename}", method = RequestMethod.GET)
	public Resource getImageSlider(@PathVariable String filename) throws MalformedURLException {
		return uploadFileService.getImageSlider(filename);
	}

	@RequestMapping(value = "cv/{filename}", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadCV(@PathVariable String filename) throws MalformedURLException {
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header("Content-Disposition", "attachment; filename=\"" + filename + "\"" )
				.body(uploadFileService.downloadCV(filename));
	}

	@RequestMapping(value = "cv", method = RequestMethod.POST)
	public String uploadCV(@RequestBody MultipartFile file) throws IOException {
		return uploadFileService.uploadCV(file);
	}
}
