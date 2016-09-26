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

import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.PriceCode;
import com.balancika.crm.services.CrmAccountTypeService;
import com.balancika.crm.services.CrmCallService;
import com.balancika.crm.services.CrmCallStatusService;
import com.balancika.crm.services.CrmCollaborationService;
import com.balancika.crm.services.CrmContactService;
import com.balancika.crm.services.CrmCustomerService;
import com.balancika.crm.services.CrmEventLocationService;
import com.balancika.crm.services.CrmEventService;
import com.balancika.crm.services.CrmIndustryService;
import com.balancika.crm.services.CrmMeetingService;
import com.balancika.crm.services.CrmMeetingStatusService;
import com.balancika.crm.services.CrmNoteService;
import com.balancika.crm.services.CrmOpportunityService;
import com.balancika.crm.services.CrmTaskService;
import com.balancika.crm.services.CrmTaskStatusService;
import com.balancika.crm.services.CrmUserService;
import com.balancika.crm.services.CustomerGroupService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CrmCustomerService customerService;
	
	@Autowired
	private CustomerGroupService groupService;
	
	@Autowired
	private CrmIndustryService industryService;
	
	@Autowired
	private CrmAccountTypeService typeService;
	
	@Autowired
	private CrmNoteService noteService;
	
	@Autowired
	private CrmCollaborationService collaborationService;
	
	@Autowired
	private CrmOpportunityService opportunityService;
	
	@Autowired
	private CrmCallService callService;
	
	@Autowired
	private CrmTaskService taskService;
	
	@Autowired
	private CrmEventService eventService;
	
	@Autowired
	private CrmMeetingService meetingService;
	
	@Autowired
	private CrmUserService userService;
	
	@Autowired
	private CrmTaskStatusService taskStatusService;
	
	@Autowired
	private CrmCallStatusService callStatusService;
	
	@Autowired
	private CrmMeetingStatusService meetingStatusService;
	
	@Autowired
	private CrmEventLocationService locationService;
	
	@Autowired
	private CrmContactService contactService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCustomers(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCustomer> arrCustomer = customerService.listCustomers();
		
		if(arrCustomer != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrCustomer);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/view/{custId}/{username}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> viewCustomer(@PathVariable("custId") String custId, @PathVariable("username") String username){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCustomer customer = customerService.viewCustomerDetails(custId);
		
		if(customer != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("CUSTOMER", customer);
			map.put("NOTES", noteService.listNoteRelatedToEachModule(custId));
			map.put("COLLABORATIONS", collaborationService.listCollaborations(custId));
			map.put("GROUP", groupService.listCustomerGroups());
			map.put("PRICE_CODE", customerService.listPriceCode());
			map.put("INDUSTRY", industryService.listIndustries());
			map.put("TYPE", typeService.listAccountTypes());
			map.put("CALLS",callService.listCallsRelatedToModule(custId));
			map.put("EVENTS", eventService.listEventsRelatedToModule(custId));
			map.put("TASKS", taskService.listTasksRelatedToModule(custId));
			map.put("MEETINGS", meetingService.listMeetingsRelatedToModule(custId));
			map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username));
			map.put("EVENT_LOCATION", locationService.listEventLocations());
			map.put("CALL_STATUS", callStatusService.listCallStatus());
			map.put("TASK_STATUS", taskStatusService.lisTaskStatus());
			map.put("MEETING_STATUS", meetingStatusService.listMeetingStatus());
			map.put("TAG_TO", userService.listAllUsernameAndId());
			map.put("CONTACTS", contactService.listSomeFieldsOfContact());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/{custID}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCustomerById(@PathVariable("custID") String custID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCustomer customer = customerService.findCustomerById(custID);
		
		if(customer != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", customer);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/add/startup", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addStartupPage(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GROUP", groupService.listCustomerGroups());
		map.put("PRICE_CODE", customerService.listPriceCode());
		map.put("INDUSTRY", industryService.listIndustries());
		map.put("TYPE", typeService.listAccountTypes());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit/startup/{custId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> editStartupPage(@PathVariable("custId") String custId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GROUP", groupService.listCustomerGroups());
		map.put("PRICE_CODE", customerService.listPriceCode());
		map.put("INDUSTRY", industryService.listIndustries());
		map.put("TYPE", typeService.listAccountTypes());
		map.put("CUSTOMER", customerService.findCustomerById(custId));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody CrmCustomer customer){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(customerService.insertCustomer(customer) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.OK.value());
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCustomer(@RequestBody CrmCustomer customer){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(customerService.updateCustomer(customer) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove/{custID}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable("custID") String custID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(customerService.deleteCustomer(custID) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping(value="/price_code/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listPriceCode(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<PriceCode> priceCodes = customerService.listPriceCode();
		
		if(priceCodes != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", priceCodes);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
}
