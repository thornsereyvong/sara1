package com.balancika.crm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.services.CrmCallService;
import com.balancika.crm.services.CrmEventService;
import com.balancika.crm.services.CrmMeetingService;
import com.balancika.crm.services.CrmNoteService;
import com.balancika.crm.services.CrmOpportunityService;
import com.balancika.crm.services.CrmTaskService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController {
	
	@Autowired
	private CrmOpportunityService opService;
	
	@Autowired
	private CrmNoteService noteService;
	
	@Autowired
	private CrmTaskService taskService;
	
	@Autowired
	private CrmEventService eventService;
	
	@Autowired
	private CrmCallService callService;
	
	@Autowired
	private CrmMeetingService meetingService;

	@RequestMapping(value="/list_all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listOpportunties(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmOpportunity> arrOpportunities = opService.listOpportunities();
		
		if(arrOpportunities != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrOpportunities);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listOpportuntiesWithSpecificUser(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> arrOpportunities = opService.listOpportunitiesWithSpecificUser(jsonMap.get("username").toString());
		
		if(arrOpportunities != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrOpportunities);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/view", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listInformationRelatedToOpportunity(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("OPPORTUNITY", opService.findOpportunityById(jsonMap.get("opId").toString()));
		map.put("CALLS", callService.listCallsRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("TASKS", taskService.listTasksRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("MEETINGS",meetingService.listTasksRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("NOTES", noteService.listNotesRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("EVENTS", eventService.listEventsRelatedToOpportunity(jsonMap.get("opId").toString()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findOpportunityById(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object opportunity = opService.findOpportunityById(opId);
		
		if(opportunity != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", opportunity);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/details/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findOpportunityDetailsById(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmOpportunity opportunity = opService.findOpportunityDetailsById(opId);
		
		if(opportunity != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", opportunity);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addNewOpportunity(@RequestBody CrmOpportunity opportunity){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(opService.isInsertOpportunity(opportunity) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateOpportunity(@RequestBody CrmOpportunity opportunity){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(opService.isUpdateOpportunity(opportunity) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove/{opId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteOpportunity(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(opService.isDeleteOpportunity(opId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
