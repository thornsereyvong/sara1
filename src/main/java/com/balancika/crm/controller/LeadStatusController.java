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

import com.balancika.crm.model.CrmLeadStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmLeadStatusService;


@RestController
@RequestMapping("/api/lead_status")
public class LeadStatusController {

	@Autowired
	private CrmLeadStatusService statusService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> getAllLeadStatus(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmLeadStatus> arrStatus = statusService.getAllLeadStatus(dataSource);
		
		if(arrStatus == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", arrStatus);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/list/{statusID}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findLeadStatusById(@PathVariable("statusID") int statusID,@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmLeadStatus status = statusService.findLeadStatusById(statusID, dataSource);
		
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
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addLeadStatus(@RequestBody CrmLeadStatus status){
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(statusService.insertLeadStatus(status) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		map.put("MESSAGE", "INSERTED");
		map.put("STATUS", HttpStatus.CREATED.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateLeadStatus(@RequestBody CrmLeadStatus status){
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(statusService.updateLeadStatus(status) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		map.put("MESSAGE", "UPADTED");
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteLeadStatus(@RequestBody CrmLeadStatus status){
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(statusService.deleteLeadStatus(status).equalsIgnoreCase("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		if(statusService.deleteLeadStatus(status).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
}
