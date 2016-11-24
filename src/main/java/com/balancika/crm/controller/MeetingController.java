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

import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMeetingService;

@RestController
@RequestMapping("/api/meeting")
public class MeetingController {

	@Autowired
	private CrmMeetingService meetingService;
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listMeetings(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmMeeting> arrMeeting = meetingService.listMeetings(dataSource);
		if(arrMeeting != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrMeeting);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/{meetingId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findMeetingById(@PathVariable("meetingId") String meetingId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		Object meeting = meetingService.findMeetingById(meetingId, dataSource);
		if(meeting != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", meeting);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/lead/{leadId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listMeetingsRelatedToLead(@PathVariable("leadId") String leadId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmMeeting> meetings = meetingService.listMeetingsRelatedToLead(leadId, dataSource);
		if(meetings != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MEETINGS", meetings);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("MEETINGS", meetings);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/opp/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listMeetingsRelatedToOpportunity(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmMeeting> meetings = meetingService.listMeetingsRelatedToOpportunity(opId, dataSource);
		if(meetings != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MEETINGS", meetings);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("MEETINGS", meetings);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/module/{moduleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listMeetingsRelatedToModule(@PathVariable("moduleId") String moduleId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmMeeting> meetings = meetingService.listMeetingsRelatedToModule(moduleId, dataSource);
		if(meetings != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MEETINGS", meetings);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("MEETINGS", meetings);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/details/{meetingId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findMeetingDetailsById(@PathVariable("meetingId") String meetingId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmMeeting meeting = meetingService.findMeetingDetailsById(meetingId, dataSource);
		if(meeting != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", meeting);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addMeeting(@RequestBody CrmMeeting meeting){
		Map<String, Object> map = new HashMap<String, Object>();
		if(meetingService.insertMeeting(meeting) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateMeeting(@RequestBody CrmMeeting meeting){
		Map<String, Object> map = new HashMap<String, Object>();
		if(meetingService.updateMeeting(meeting) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	

	@RequestMapping(value="/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteMeeting(@RequestBody CrmMeeting meeting){
		Map<String, Object> map = new HashMap<String, Object>();
		if(meetingService.deleteMeeting(meeting) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
