package com.balancika.crm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileUploadController {

	@RequestMapping(value="/upload/{srcFolder}",method = RequestMethod.POST)
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
				map.put("msg", "failed");
			}
        map.put("fileNames", fileNames);
        map.put("msg", "success");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/file/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> removeExistFile(@RequestParam("srcFolder") String srcFolder,@RequestParam("fileName") String fileName, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String realPath = request.getSession().getServletContext().getRealPath("/");
			File fileToDelete = FileUtils.getFile(realPath+"/resources/"+srcFolder+"/"+fileName);
			boolean success = FileUtils.deleteQuietly(fileToDelete);
			if(success){
				map.put("msg", "removed"); 
				map.put("status", HttpStatus.OK.value());
			}else{
				map.put("msg", "failed");
				map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/*@RequestMapping(value = {"/get/{fileName:.*}"}, method = RequestMethod.POST)
	public byte[] get(HttpServletRequest servletRequest,  @PathVariable("fileName") String fileName) {
		try {
				File f = 
				if(f != null){
					InputStream is = new FileInputStream(f);
					return IOUtils.toByteArray(is);
				}
			
				//return tool.SPSelectFile(meDataSource, "spHRMGetFile", fileName);
		} catch (Exception e) {
			return null;
		}
		return null;
	}*/
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/file/get"}, method = RequestMethod.POST)
	public byte[] getFile(HttpServletRequest servletRequest,  @RequestBody String path, HttpServletRequest request) {
		System.out.println(path);
		try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(path, Map.class);
				String realPath = request.getSession().getServletContext().getRealPath("/");
				File f = FileUtils.getFile(realPath+""+map.get("path"));
				if(f != null){
					InputStream is = new FileInputStream(f);
					return IOUtils.toByteArray(is);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
