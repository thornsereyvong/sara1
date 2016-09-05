package com.balancika.crm.controller;

import java.io.IOException;
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

import com.balancika.crm.model.CrmLead;
import com.balancika.crm.services.CrmLeadService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/lead")
public class LeadController {
	
	@Autowired
	private CrmLeadService leadService;
	
	@RequestMapping(value = "/list_all", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllLead(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmLead> arrLead = leadService.getAllLead();
		if(arrLead.isEmpty()){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("DATA", arrLead);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", arrLead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getLeadBySpecificUser(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> userMap = new HashMap<String, String>();
		try {
			userMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmLead> arrLead = leadService.getLeadBySpecificUser(userMap.get("username").toString());
		
		if(arrLead.isEmpty()){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("DATA", arrLead);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", arrLead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{leadID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findLeadById(@PathVariable("leadID") String leadID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Object Lead = leadService.findLeadById(leadID);
		
		if(Lead == null){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
	
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", Lead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/details/{leadID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findLeadDetailsById(@PathVariable("leadID") String leadID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmLead Lead = leadService.findLeadDetailById(leadID);
		
		if(Lead == null){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
	
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", Lead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addLead(@RequestBody CrmLead lead){
		
		Map<String , Object> map = new HashMap<String, Object>();
		
		if(leadService.insertLead(lead) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateList(@RequestBody CrmLead lead){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(leadService.updateLead(lead) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/remove/{leadID}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteList(@PathVariable("leadID") String leadID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(leadService.deleteLead(leadID) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> convertLead(@RequestBody String json){
		
		Map<String , Object> map = new HashMap<String, Object>();
		if(leadService.convertLead(json) == true){
			map.put("MESSAGE", "CONVERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
