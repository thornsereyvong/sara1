package com.balancika.crm.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

	@Autowired
	private MultipartResolver multipartResolver;
	
	@RequestMapping(value="/{srcFolder}",method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> upload(@RequestParam(value = "file", required = false) MultipartFile[] files,HttpServletRequest request, @PathVariable("srcFolder") String srcFolder){
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> fileNames= new ArrayList<String>();
        String savePath = request.getSession().getServletContext().getRealPath("/");
        String filePath = savePath + "resources/" + srcFolder;
        String pathAndFilename = "/resources/"+srcFolder;
        File f = new File(filePath);
        String randomFileName = "";
        if(!f.exists()){
        	f.mkdir();
        }
        for(MultipartFile file : files)
        	try {
        		randomFileName = UUID.randomUUID()+"."+file.getOriginalFilename().split("\\.")[1];
				FileCopyUtils.copy(file.getBytes(), new File(filePath +File.separator+ randomFileName));
				fileNames.add(pathAndFilename+"/"+randomFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
        map.put("fileNames", fileNames);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
