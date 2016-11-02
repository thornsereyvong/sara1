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

import com.balancika.crm.model.CrmOpportunityDetails;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityDetailsService;

@RestController
@RequestMapping("/api/opportunity_details")
public class OpportunityDetailsController {

	@Autowired
	private CrmOpportunityDetailsService opportunityDetailsService;
	
	@RequestMapping(value="/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupPage(@RequestBody MeDataSource dataSource){
		return new ResponseEntity<Map<String,Object>>(opportunityDetailsService.startUpPage(dataSource), HttpStatus.OK);
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listOpportunityDetails(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmOpportunityDetails> details = opportunityDetailsService.listOpportunityDetails(dataSource);
		if(details != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", details);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", details);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{opDetailsId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findOpportunityById(@PathVariable("opDetailsId") int opDetailsId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmOpportunityDetails details = opportunityDetailsService.findOpportunityDetailsById(opDetailsId, dataSource);
		if(details != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", details);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", details);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addOpportunity(@RequestBody CrmOpportunityDetails details){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityDetailsService.insertOpportunityDetails(details) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("DATA", opportunityDetailsService.findOpportunityDetailsById(details.getOpDetailsId(), details.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> editOpportunity(@RequestBody CrmOpportunityDetails details){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityDetailsService.updateOpportunityDetails(details) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> deleteOpportunity(@RequestBody CrmOpportunityDetails details){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityDetailsService.deleteOpportunityDetails(details) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
