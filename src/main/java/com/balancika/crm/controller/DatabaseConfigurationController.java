package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmDatabaseConfiguration;
import com.balancika.crm.services.CrmDatabaseConfigurationService;

@RestController
@RequestMapping("/api/config")
public class DatabaseConfigurationController {

	@Autowired
	private CrmDatabaseConfigurationService configurationService;
	
	@Autowired
	private CrmDatabaseConfiguration config;
	
	@RequestMapping(value = "/database", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getDatabaseName(@RequestBody CrmDatabaseConfiguration configuration){
		Map<String, Object>  map = new HashMap<String, Object>();
		config.setDbIP(configuration.getDbIP());
		config.setDbName(configuration.getDbName());
		config.setDbUsername(configuration.getDbUsername());
		config.setDbPassword(configuration.getDbPassword());
		config.setDbPort(configuration.getDbPort());
		map.put("MESSAGE", "OK");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/database/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listDataBase(HttpServletRequest request){
		Map<String, Object>  map = new HashMap<String, Object>();
		config.setDbIP(request.getHeader("dbIP"));
		config.setDbName(request.getHeader("dbName"));
		config.setDbUsername(request.getHeader("dbUsername"));
		config.setDbPassword(request.getHeader("dbPassword"));
		config.setDbPort(request.getHeader("dbPort"));
		map.put("MESSAGE", "OK");
		map.put("DATABASE", configurationService.listDatabases());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
