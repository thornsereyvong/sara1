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

import com.balancika.crm.model.CrmEventLocation;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmEventLocationService;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmUserActivityService;

@RestController
@RequestMapping("/api/event_location")
public class EventLocationController {

	@Autowired
	private CrmEventLocationService locationService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listEventLocations(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmEventLocation> arrLocation = locationService.listEventLocations(dataSource);
		if(arrLocation != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrLocation);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findEventLocationById(@PathVariable("id") String id, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmEventLocation location = locationService.findEventLocationById(id, dataSource);
		if(location != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", location);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addEventLocation(@RequestBody CrmEventLocation location){
		Map<String, Object> map = new HashMap<String, Object>();
		if(locationService.insertEventLocation(location) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			
			map.put("MSG", messageService.getMessage("1000", "location", location.getLoId(), location.getMeDataSource()));			
			activityService.addUserActivity(activity.getActivity(location.getMeDataSource(), "Create", "Location", location.getLoId()));
			
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		map.put("MSG", messageService.getMessage("1003", "location", "", location.getMeDataSource()));
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateEventLocation(@RequestBody CrmEventLocation location){
		Map<String, Object> map = new HashMap<String, Object>();
		if(locationService.updateEventLocation(location) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1001", "location", location.getLoId(), location.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(location.getMeDataSource(), "Update", "Location", location.getLoId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "location", location.getLoId(), location.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteEventLocation(@RequestBody CrmEventLocation location){
		Map<String, Object> map = new HashMap<String, Object>();
		if(locationService.deleteEventLocation(location).equals("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1001", "location", location.getLoId(), location.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(location.getMeDataSource(), "Update", "Location", location.getLoId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(locationService.deleteEventLocation(location).equals("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1010", "location", location.getLoId(), location.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "location", location.getLoId(), location.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
