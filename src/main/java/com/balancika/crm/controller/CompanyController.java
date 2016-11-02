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

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CompanyService;

@RestController
@RequestMapping("/api/config")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	
	@RequestMapping(value = "/database", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getDatabaseName(@RequestBody MeDataSource dataSource){
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("MESSAGE", "OK");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/database/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listDatabase(@RequestBody MeDataSource meDataSource){
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("MESSAGE", "OK");
		map.put("DATABASE", companyService.listDatabases(meDataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
}
