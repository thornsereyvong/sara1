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

import com.balancika.crm.model.CrmOpportunityType;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityTypeService;

@RestController
@RequestMapping("/api/op_type")
public class OpportunityTypeController {

	
	@Autowired
	private CrmOpportunityTypeService typeService;
	
	
	@RequestMapping(value="/list", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listOpportunityTypes(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmOpportunityType> arrType = typeService.listOpportunityTypes(dataSource);
		
		if(arrType != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrType);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{typeID}", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findOpportunityTypeById(@PathVariable("typeID") int typeID, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmOpportunityType type = typeService.findOpportunityTypeById(typeID, dataSource);
		
		if(type != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", type);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addOpportunityType(@RequestBody CrmOpportunityType type){
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(typeService.insertOpportunityType(type) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateOpportunityType(@RequestBody CrmOpportunityType type){
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(typeService.updateOpportunityType(type) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteOpportunityTypeById(@RequestBody CrmOpportunityType type){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(typeService.deleteOpportunityType(type).equalsIgnoreCase("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(typeService.deleteOpportunityType(type).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
