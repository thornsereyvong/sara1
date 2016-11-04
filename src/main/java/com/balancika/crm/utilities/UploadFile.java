package com.balancika.crm.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class UploadFile {
	
	private MultipartFile files;

	public MultipartFile getFiles() {
		return files;
	}

	public void setFiles(MultipartFile files) {
		this.files = files;
	}

public String UploadFiles(MultipartFile file, String savePath,String fileName) {
		
		String filename = file.getOriginalFilename();
		String pathAndFileName="/resources/upload/img/";
		if (!file.isEmpty()) {
			try {				
				byte[] bytes = file.getBytes();
				File path = new File(savePath);				
				if (!path.exists()) {
					path.mkdirs();
				}
				File serverFile = new File(savePath + File.separator + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println(serverFile.getAbsolutePath());
				pathAndFileName+="/"+fileName;
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("You are failed to upload " + fileName + " => " + e.getMessage());
			}
		} else {
			System.out.println("You are failed to upload " + filename + " because the file was empty!");
		}

		return pathAndFileName;
	}
}
