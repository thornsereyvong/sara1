package com.balancika.crm.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import com.balancika.crm.model.FileUpload;
import com.balancika.crm.utilities.FileVailidator;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

	@Autowired
	private MultipartResolver multipartResolver;
	
	@Autowired
	private FileVailidator fileVailidator; 
	
	@RequestMapping(name="/image", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> uploadFile(@Valid @RequestParam("file") FileUpload file, BindingResult result, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/image");
		File path = new File(savePath);
		if(!path.exists()){
			path.mkdirs();
		}
		if (result.hasErrors()) {
            map.put("MESSAGE", "File Not found!");
        } else {            
            MultipartFile multipartFile = file.getFileUplaod();
            try {
				FileCopyUtils.copy(file.getFileUplaod().getBytes(), new File(savePath + file.getFileUplaod().getOriginalFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
            String fileName = multipartFile.getOriginalFilename();
            map.put("fileName", fileName);
            System.out.println(map.get("fileName"));
        }
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
