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

import com.balancika.crm.model.CrmContact;
import com.balancika.crm.services.CrmCallService;
import com.balancika.crm.services.CrmCallStatusService;
import com.balancika.crm.services.CrmCollaborationService;
import com.balancika.crm.services.CrmContactService;
import com.balancika.crm.services.CrmCustomerService;
import com.balancika.crm.services.CrmEventLocationService;
import com.balancika.crm.services.CrmEventService;
import com.balancika.crm.services.CrmLeadSourceService;
import com.balancika.crm.services.CrmMeetingService;
import com.balancika.crm.services.CrmMeetingStatusService;
import com.balancika.crm.services.CrmNoteService;
import com.balancika.crm.services.CrmTaskService;
import com.balancika.crm.services.CrmTaskStatusService;
import com.balancika.crm.services.CrmUserService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
	
	@Autowired
	private CrmContactService contactService;
	
	@Autowired
	private CrmCustomerService customerService;
	
	@Autowired
	private CrmLeadSourceService sourceService;
	
	@Autowired
	private CrmUserService userService;
	
	@Autowired
	private CrmCollaborationService collaborationService;
	
	@Autowired
	private CrmNoteService noteService;
	
	@Autowired
	private CrmCallService callService;
	
	@Autowired
	private CrmTaskService taskService;
	
	@Autowired
	private CrmEventService eventService;
	
	@Autowired
	private CrmMeetingService meetingService;
	
	@Autowired
	private CrmTaskStatusService taskStatusService;
	
	@Autowired
	private CrmCallStatusService callStatusService;
	
	@Autowired
	private CrmMeetingStatusService meetingStatusService;
	
	@Autowired
	private CrmEventLocationService eventLocationService;

	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listContacts(){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmContact> arrContact = contactService.listContacts();
		if( arrContact == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", arrContact);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{conId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findContactById(@PathVariable("conId") String conId){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		Object contact = contactService.findContactById(conId);
		if( contact == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", contact);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/view/{conId}/{username}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> viewContact(@PathVariable("conId") String conId, @PathVariable("username") String username){
	
		Map<String, Object> map = new HashMap<String, Object>();
		map = contactService.viewContact(conId);
		map.put("CUSTOMERS", customerService.listCustomerIdAndName());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource());
		map.put("REPORT_TO", contactService.listParentOfContact());
		map.put("COLLABORATIONS", collaborationService.listCollaborations(conId));
		map.put("NOTES", noteService.listNoteRelatedToEachModule(conId));
		map.put("CALLS", callService.listCallsRelatedToModule(conId));
		map.put("TASKS", taskService.listTasksRelatedToModule(conId));
		map.put("METTINGS", meetingService.listMeetingsRelatedToModule(conId));
		map.put("EVENTS", eventService.listEventsRelatedToModule(conId));
		map.put("TAG_TO", userService.listAllUsernameAndId());
		map.put("MEETING_STATUS", meetingStatusService.listMeetingStatus());
		map.put("TASK_STATUS", taskStatusService.lisTaskStatus());
		map.put("CALL_STATUS", callStatusService.listCallStatus());
		map.put("EVENT_LOCATION", eventLocationService.listEventLocations());
		map.put("CONTACTS", contactService.listSomeFieldsOfContact());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/details/{conId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findContactDetailsById(@PathVariable("conId") String conId){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmContact contact = contactService.findContactDetailsById(conId);
		if( contact == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", contact);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/startup/{username}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addStartupPage(@PathVariable("username") String username){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CUSTOMERS", customerService.listCustomerIdAndName());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource());
		map.put("REPORT_TO", contactService.listParentOfContact());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addContact(@RequestBody CrmContact contact){
	
		Map<String, Object> map = new HashMap<String, Object>();
		if(contactService.insertContact(contact) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		map.put("MESSAGE", "INSERTED");
		map.put("STATUS", HttpStatus.CREATED.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}

	@RequestMapping(value="/startup/{username}/{conId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> editStartupPage(@PathVariable("username") String username, @PathVariable("conId") String conId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CUSTOMERS", customerService.listCustomerIdAndName());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource());
		map.put("REPORT_TO", contactService.listParentOfContact());
		map.put("CONTACT", contactService.findContactById(conId));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value="/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateContact(@RequestBody CrmContact contact){
		Map<String, Object> map = new HashMap<String, Object>();
		if(contactService.updateContact(contact) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		map.put("MESSAGE", "UPDATED");
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove/{conId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteContact(@PathVariable("conId") String conId){
	
		Map<String, Object> map = new HashMap<String, Object>();
		if(contactService.deleteContact(conId) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		map.put("MESSAGE", "DELETED");
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
