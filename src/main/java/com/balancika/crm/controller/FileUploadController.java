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

import com.balancika.crm.utilities.UploadFile;

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
	
	@RequestMapping(method = RequestMethod.POST, value = "/image/{srcFolder}")
	public ResponseEntity<Map<String, Object>> uploadimage(
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, @PathVariable("srcFolder") String srcFolder) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ramdom_file_name="";
		try {
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/file/");
			UploadFile fileName = new UploadFile();
			if (file == null) {
				map.put("STATUS", false);
				map.put("MESSAGE", "IMAGE HAS NOT BEEN INSERTED");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} else {
				ramdom_file_name = UUID.randomUUID() + ".jpg";
				String CategoryImage = fileName.UploadFiles(file, savePath,ramdom_file_name,srcFolder);
				map.put("STATUS", HttpStatus.OK.value());
				map.put("MESSAGE", "IMAGE HAS BEEN INSERTED");
				map.put("FILENAME", CategoryImage);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "OPERATION FAIL");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/attachment/{srcFolder}")
	public ResponseEntity<Map<String, Object>> uploadAttachment(
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, @PathVariable("srcFolder") String srcFolder) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/file/");
			UploadFile fileName = new UploadFile();
			if (file == null) {
				map.put("STATUS", false);
				map.put("MESSAGE", "IMAGE HAS NOT BEEN INSERTED");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} else {
				String imagePath = fileName.UploadFiles(file, savePath,file.getOriginalFilename(),srcFolder);
				map.put("STATUS", HttpStatus.OK.value());
				map.put("MESSAGE", "IMAGE HAS BEEN INSERTED");
				map.put("FILENAME", imagePath);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}
		} catch (Exception e) {
			map.put("MESSAGE", "OPERATION FAIL");
			map.put("STATUS", false);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
