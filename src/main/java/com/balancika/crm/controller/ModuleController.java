package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmModule;

@RestController
@RequestMapping("/api/module")
public class ModuleController {
	
	@Autowired
	private CrmModule moduleService;
	
	@RequestMapping(value = "/list/{module}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getItemsByItsModuleName(@PathVariable("module") String module, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<?> arrList = moduleService.listModuleDetailsByModuleName(module, dataSource);
		if(arrList != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrList);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listSystemModules(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> arrList = moduleService.listSystemModules(dataSource);
		if(arrList != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrList);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}

}
