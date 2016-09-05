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

import com.balancika.crm.model.CrmLeadSource;
import com.balancika.crm.services.CrmLeadSourceService;

@RestController
@RequestMapping("/api/lead_source")
public class LeadSourceController {

	@Autowired
	private CrmLeadSourceService sourceService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> getAllLeadSource(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmLeadSource> arrSource = sourceService.getAllLeadSource();
		
		if(arrSource.isEmpty() || arrSource == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", arrSource);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{sourceID}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findLeadSourceById(@PathVariable("sourceID") int sourceID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmLeadSource source = sourceService.findLeadSourceById(sourceID);
		
		if(source == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", source);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addLeadSource(@RequestBody CrmLeadSource source){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(sourceService.insertLeadSource(source) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		map.put("MESSAGE", "INSERTED");
		map.put("STATUS", HttpStatus.CREATED.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateLeadSource(@RequestBody CrmLeadSource source){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(sourceService.updateLeadSource(source) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		map.put("MESSAGE", "UPDATED");
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove/{sourceId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteLeadSource(@PathVariable("sourceId") int sourceId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(sourceService.deleteLeadSource(sourceId).equalsIgnoreCase("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		if(sourceService.deleteLeadSource(sourceId).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
