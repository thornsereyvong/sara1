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
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
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
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmNoteService;
import com.balancika.crm.services.CrmTaskService;
import com.balancika.crm.services.CrmTaskStatusService;
import com.balancika.crm.services.CrmUserActivityService;
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
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listContacts(@RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmContact> arrContact = contactService.listContacts(dataSource);
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
	
	@RequestMapping(value="/list/{conId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findContactById(@PathVariable("conId") String conId, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		Object contact = contactService.findContactById(conId, dataSource);
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
	
	@RequestMapping(value="/view/{conId}/{username}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> viewContact(@PathVariable("conId") String conId, @PathVariable("username") String username, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		map = contactService.viewContact(conId, dataSource);
		map.put("CUSTOMERS", customerService.listCustomerIdAndName(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource(dataSource));
		map.put("REPORT_TO", contactService.listParentOfContact(dataSource));
		map.put("COLLABORATIONS", collaborationService.listCollaborations(conId, dataSource));
		map.put("NOTES", noteService.listNoteRelatedToEachModule(conId, dataSource));
		map.put("CALLS", callService.listCallsRelatedToModule(conId, dataSource));
		map.put("TASKS", taskService.listTasksRelatedToModule(conId, dataSource));
		map.put("METTINGS", meetingService.listMeetingsRelatedToModule(conId, dataSource));
		map.put("EVENTS", eventService.listEventsRelatedToModule(conId, dataSource));
		map.put("TAG_TO", userService.listAllUsernameAndId(dataSource));
		map.put("MEETING_STATUS", meetingStatusService.listMeetingStatus(dataSource));
		map.put("TASK_STATUS", taskStatusService.lisTaskStatus(dataSource));
		map.put("CALL_STATUS", callStatusService.listCallStatus(dataSource));
		map.put("EVENT_LOCATION", eventLocationService.listEventLocations(dataSource));
		map.put("CONTACTS", contactService.listSomeFieldsOfContact(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/details/{conId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findContactDetailsById(@PathVariable("conId") String conId, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmContact contact = contactService.findContactDetailsById(conId, dataSource);
		if( contact == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MSG", "NOT_FOUND");
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", contact);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/startup/{username}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addStartupPage(@PathVariable("username") String username, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CUSTOMERS", customerService.listCustomerIdAndName(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource(dataSource));
		map.put("REPORT_TO", contactService.listParentOfContact(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addContact(@RequestBody CrmContact contact){
	
		Map<String, Object> map = new HashMap<String, Object>();
		if(contactService.insertContact(contact) == false){
			map.put("MESSAGE", "FAILED");			
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1003", "contact", "", contact.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "INSERTED");
		map.put("STATUS", HttpStatus.CREATED.value());		
		map.put("MSG", messageService.getMessage("1000", "contact", contact.getConID(), contact.getMeDataSource()));
		
		activityService.addUserActivity(activity.getActivity(contact.getMeDataSource(), "Create", "Contact", contact.getConID()));
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}

	@RequestMapping(value="/startup/{username}/{conId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> editStartupPage(@PathVariable("username") String username, @PathVariable("conId") String conId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CUSTOMERS", customerService.listCustomerIdAndName(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource(dataSource));
		map.put("REPORT_TO", contactService.listParentOfContact(dataSource));
		map.put("CONTACT", contactService.findContactById(conId, dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value="/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateContact(@RequestBody CrmContact contact){
		Map<String, Object> map = new HashMap<String, Object>();
		if(contactService.updateContact(contact) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1004", "contact", contact.getConID(), contact.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "UPDATED");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MSG", messageService.getMessage("1001", "contact", contact.getConID(), contact.getMeDataSource()));
		activityService.addUserActivity(activity.getActivity(contact.getMeDataSource(), "Update", "Contact", contact.getConID()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteContact(@RequestBody CrmContact contact){
	
		Map<String, Object> map = new HashMap<String, Object>();
		if(contactService.deleteContact(contact) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1005", "contact", contact.getConID(), contact.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "DELETED");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MSG", messageService.getMessage("1002", "contact", contact.getConID(), contact.getMeDataSource()));
		activityService.addUserActivity(activity.getActivity(contact.getMeDataSource(), "Delete", "Contact", contact.getConID()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
