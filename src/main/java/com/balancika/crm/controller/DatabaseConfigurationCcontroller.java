package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.services.CrmDatabaseConfigurationService;

@RestController
@RequestMapping("/api/database")
public class DatabaseConfigurationCcontroller {

	@Autowired
	private CrmDatabaseConfigurationService configurationService;
	
	@RequestMapping(value = "/{dbName}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getDatabaseName(@PathVariable("dbName") String dbName){
		Map<String, Object>  map = new HashMap<String, Object>();
		configurationService.changeDataSource(dbName);
		map.put("MESSAGE", "OK");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
