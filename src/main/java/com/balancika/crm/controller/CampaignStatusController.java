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

import com.balancika.crm.model.CrmCampaignStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCampaignStatusService;

@RestController
@RequestMapping("/api/camp_status")
public class CampaignStatusController {
	
	@Autowired
	private CrmCampaignStatusService statusService;
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listCampaignStatus(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmCampaignStatus> arrStatus = statusService.listAllCampaignStatus(dataSource);
		
		if(arrStatus == null){
			
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
			
		}else if(arrStatus.isEmpty()){
			
			map.put("MESSAGE", "NO_CONTENT");
			map.put("STATUS", HttpStatus.NO_CONTENT.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NO_CONTENT);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA",arrStatus);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{statusID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findCampaignStatusById(@PathVariable("statusID") int statusID, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCampaignStatus status = statusService.findCampaignStatusById(statusID, dataSource);
		if(status == null){
			
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
			
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", status);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addCampaignStatus(@RequestBody CrmCampaignStatus status){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(statusService.addCampaignStatus(status) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@RequestMapping(value="/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> editCampaignStatus(@RequestBody CrmCampaignStatus status){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(statusService.updateCampaignStatus(status) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deletCampaignStatus(@RequestBody CrmCampaignStatus status){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(statusService.deleteCampaignStatus(status).equals("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(statusService.deleteCampaignStatus(status).equals("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
