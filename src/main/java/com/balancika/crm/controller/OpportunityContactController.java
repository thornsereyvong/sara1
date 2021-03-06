package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmOpportunityContact;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityContactService;

@RestController
@RequestMapping("/api/opportunity_contact")
public class OpportunityContactController {

	@Autowired
	private CrmOpportunityContactService opportunityContactService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addOpportunityContact(@RequestBody CrmOpportunityContact opCon){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityContactService.checkOpportunityContactIsExist(opCon.getOpId(), opCon.getConId(), opCon.getMeDataSource()) > 0){
			map.put("MESSAGE", "EXIST");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}else{
			if(opportunityContactService.insterOpportunityContact(opCon) == true){
				map.put("MESSAGE", "INSERTED");
				map.put("DATA", opportunityContactService.viewOpportunityContactById(opCon.getOpConId(), opCon.getMeDataSource()));
				map.put("STATUS", HttpStatus.CREATED.value());
				return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
			}
			
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateOpportunityContact(@RequestBody CrmOpportunityContact opCon){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityContactService.updateOpportunityContact(opCon) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOpportunityContact(@RequestBody CrmOpportunityContact opCon){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityContactService.deleteOpportunityContact(opCon) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/{opConId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> viewOpportunityContact(@PathVariable("opConId") int opConId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmOpportunityContact opportunityContact = opportunityContactService.findOpportunityContactById(opConId, dataSource);
		if(opportunityContact != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("OPPORTUNITY_CONTACT", opportunityContact);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("OPPORTUNITY_CONTACT", opportunityContact);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
