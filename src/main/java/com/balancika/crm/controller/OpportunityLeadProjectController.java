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

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityLeadProjectService;

@RestController
@RequestMapping("/api/op/project")
public class OpportunityLeadProjectController {

	@Autowired
	private CrmOpportunityLeadProjectService projectService;
	
	@RequestMapping(value="/add/{opId}/{lpId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addOpportunityLeadProject(@PathVariable("opId") String opId, @PathVariable("lpId") int lpId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		if(projectService.addOpportunityLeadProject(opId, lpId, dataSource) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("OP_PROJECT", projectService.listOpportunityLeadProjectByOpId(opId, dataSource));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupLeadProject(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = projectService.startupOpportunityLeadProject(dataSource);
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove/{opId}/{lpId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOpportunityLeadProject(@PathVariable("opId") String opId, @PathVariable("lpId") int lpId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		if(projectService.deleteOpportunityLeadProject(opId, lpId, dataSource) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("OP_PROJECT", projectService.listOpportunityLeadProjectByOpId(opId, dataSource));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
