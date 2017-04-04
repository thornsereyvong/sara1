package com.balancika.crm.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmMeetingCheckin;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMeetingService;

@RestController
@RequestMapping("/api/mobile/meeting")
public class MeetingMobileController {

	@Autowired
	private CrmMeetingService meetingService;
	
	@RequestMapping(value="/list/{rowNum}/{pageNum}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listMeetings(@PathVariable("rowNum") int rowNum, @PathVariable("pageNum") int pageNum, @RequestBody MeDataSource dataSource){
		return new ResponseEntity<Map<String,Object>>(meetingService.listMeetingsForMobile(rowNum, pageNum, dataSource), HttpStatus.OK);
	}
	
	@RequestMapping(value="/checkin", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> meetingCheckin(@RequestBody CrmMeetingCheckin checkin){
		return new ResponseEntity<Map<String,Object>>(meetingService.meetingCheckIn(checkin), HttpStatus.OK);
	}
}
