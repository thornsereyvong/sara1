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

import com.balancika.crm.model.CrmCollaboration;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCollaborationService;
import com.balancika.crm.services.CrmUserService;

@RestController
@RequestMapping("/api/collaboration")
public class CollaborationController {
	
	@Autowired
	private CrmCollaborationService collaborationService;
	
	@Autowired
	private CrmUserService userService;
	
	
	@RequestMapping(value = "/list/{moduleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCollaborations(@RequestBody MeDataSource dataSource, @PathVariable("moduleId") String moduleId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCollaboration> collaborations = collaborationService.listCollaborations(moduleId, dataSource);
		if(collaborations != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", collaborations);
			map.put("TAG_TO", userService.listAllUsernameAndId(dataSource));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", collaborations);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCollaboration(@RequestBody CrmCollaboration collaboration){
		Map<String, Object> map = new HashMap<String, Object>();
		if(collaborationService.insertCollaboration(collaboration) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("COLLABORATION", collaborationService.findCollaborationById(collaboration.getColId(), collaboration.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> editCollaboration(@RequestBody CrmCollaboration collaboration){
		Map<String, Object> map = new HashMap<String, Object>();
		if(collaborationService.updateCollaboration(collaboration) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCollaboration(@RequestBody CrmCollaboration collaboration){
		Map<String, Object> map = new HashMap<String, Object>();
		if(collaborationService.deleteCollaboration(collaboration) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
