package com.balancika.crm.utilities;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.balancika.crm.model.FileUpload;

@Component
public class FileVailidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return FileUpload.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileUpload file = (FileUpload)target;
		if(file.getFileUplaod()!=null){
            if (file.getFileUplaod().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
	}

}
