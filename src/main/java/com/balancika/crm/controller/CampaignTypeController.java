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

import com.balancika.crm.model.CrmCampaignType;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCampaignTypeService;

@RestController
@RequestMapping("/api/camp_type")
public class CampaignTypeController {
	
	@Autowired
	private CrmCampaignTypeService typeService;
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> getCampaignTypes(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmCampaignType> arrType = typeService.listAllCampaignType(dataSource);
		if(arrType != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA",arrType);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/{typeID}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCampaignTypeById(@PathVariable("typeID") int typeID, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmCampaignType type = typeService.findCampaignTypeById(typeID,dataSource);
		if(type != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA",type);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST , produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCampaignType(@RequestBody CrmCampaignType type){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(typeService.addCampaignType(type) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT , produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCampaignType(@RequestBody CrmCampaignType type){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(typeService.updateCampaignType(type) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST , produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCampaignType(@RequestBody CrmCampaignType type){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(typeService.deleteCampaignType(type).equals("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(typeService.deleteCampaignType(type).equals("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
