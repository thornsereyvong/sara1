package com.balancika.crm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import com.balancika.crm.utilities.FileVailidator;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

	@Autowired
	private MultipartResolver multipartResolver;
	
	@Autowired
	private FileVailidator fileVailidator; 
	
	
	/*public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file){
		
	}*/
}
