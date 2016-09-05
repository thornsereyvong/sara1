package com.balancika.crm.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/database")
public class DatabaseConfigurationCcontroller {

	
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getDatabaseName(@RequestBody String json){
		System.out.println("json");
		return null;
	}
}
