package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.MailMessage;
import com.balancika.crm.services.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;

	@RequestMapping(value="/send", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> sendMail(@RequestBody MailMessage mailMessage){
		Map<String, Object> map = new HashMap<String, Object>();
		if(mailService.sendEmail(mailMessage) == true){
			map.put("MESSAGE", "SENT");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
