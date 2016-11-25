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

import com.balancika.crm.model.CrmMeetingStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMeetingStatusService;

@RestController
@RequestMapping("/api/meeting_status")
public class MeetingStatusController {

	@Autowired
	private CrmMeetingStatusService statusService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listMeetingStatus(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmMeetingStatus> arrStatus = statusService.listMeetingStatus(dataSource);
		if(arrStatus != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS",HttpStatus.OK.value());
			map.put("DATA", arrStatus);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS",HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{statusId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findMeetingStatusById(@PathVariable("statusId") int statusId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmMeetingStatus status = statusService.findMeetingStatusById(statusId, dataSource);
		if(status != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS",HttpStatus.OK.value());
			map.put("DATA", status);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS",HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addMeetingStatus(@RequestBody CrmMeetingStatus status){
		Map<String, Object> map = new HashMap<String, Object>();
		if(statusService.insertMeetingStatus(status) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS",HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS",HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateMeetingStatus(@RequestBody CrmMeetingStatus status){
		Map<String, Object> map = new HashMap<String, Object>();
		if(statusService.updateMeetingStatus(status) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS",HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS",HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteMeetingStatus(@RequestBody CrmMeetingStatus status){
		Map<String, Object> map = new HashMap<String, Object>();
		if(statusService.deleteMeetingStatus(status).equalsIgnoreCase("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS",HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		if(statusService.deleteMeetingStatus(status).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS",HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS",HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
