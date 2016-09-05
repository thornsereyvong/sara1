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

import com.balancika.crm.model.CrmCasePriority;
import com.balancika.crm.services.CrmCasePriorityService;

@RestController
@RequestMapping("/api/case_priority")
public class CasePriorityController {
	
	@Autowired
	private CrmCasePriorityService priorityService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCasePriorities(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCasePriority> arrPriority = priorityService.listCasePriorities();
		if(arrPriority != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrPriority);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value="/list/{priorityId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCasePriorityById(@PathVariable("priorityId") int priorityId){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCasePriority priority = priorityService.findCasePriorityById(priorityId);
		if(priority != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", priority);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCasePriority(@RequestBody CrmCasePriority priority){
		Map<String, Object> map = new HashMap<String, Object>();
		if(priorityService.insertCasePriority(priority) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCasePriority(@RequestBody CrmCasePriority casePriority){
		Map<String, Object> map = new HashMap<String, Object>();
		if(priorityService.updateCasePriority(casePriority) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove/{priorityId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCasePriority(@PathVariable("priorityId") int priorityId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(priorityService.deleteCasePriority(priorityId).equalsIgnoreCase("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(priorityService.deleteCasePriority(priorityId).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
