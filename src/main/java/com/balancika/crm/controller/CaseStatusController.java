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

import com.balancika.crm.model.CrmCaseStatus;
import com.balancika.crm.services.CrmCaseStatusService;

@RestController
@RequestMapping("/api/case_status")
public class CaseStatusController {

	@Autowired
	private CrmCaseStatusService statusService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCaseStatus(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCaseStatus> arrStatus = statusService.listCaseStatus();
		if(arrStatus != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrStatus);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/{statusId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCaseStatusById(@PathVariable("statusId") int statusId){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCaseStatus status = statusService.findCaseStatusById(statusId);
		if(status != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", status);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCaseStatus(@RequestBody CrmCaseStatus status){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(statusService.insertCaseStatus(status) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCaseStatus(@RequestBody CrmCaseStatus status){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(statusService.updateCaseStatus(status) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove/{statusId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCaseStatus(@PathVariable("statusId") int statusId){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(statusService.deleteCaseStatus(statusId).equalsIgnoreCase("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(statusService.deleteCaseStatus(statusId).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
