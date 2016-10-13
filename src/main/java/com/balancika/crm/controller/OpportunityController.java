package com.balancika.crm.controller;

import java.io.IOException;
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

import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.services.CrmCallService;
import com.balancika.crm.services.CrmCallStatusService;
import com.balancika.crm.services.CrmCampaignService;
import com.balancika.crm.services.CrmCollaborationService;
import com.balancika.crm.services.CrmContactService;
import com.balancika.crm.services.CrmCustomerService;
import com.balancika.crm.services.CrmEventLocationService;
import com.balancika.crm.services.CrmEventService;
import com.balancika.crm.services.CrmLeadSourceService;
import com.balancika.crm.services.CrmMeetingService;
import com.balancika.crm.services.CrmMeetingStatusService;
import com.balancika.crm.services.CrmNoteService;
import com.balancika.crm.services.CrmOpportunityDetailsService;
import com.balancika.crm.services.CrmOpportunityService;
import com.balancika.crm.services.CrmOpportunityStageService;
import com.balancika.crm.services.CrmOpportunityTypeService;
import com.balancika.crm.services.CrmTaskService;
import com.balancika.crm.services.CrmTaskStatusService;
import com.balancika.crm.services.CrmUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController {
	
	@Autowired
	private CrmOpportunityService opService;
	
	@Autowired
	private CrmNoteService noteService;
	
	@Autowired
	private CrmTaskService taskService;
	
	@Autowired
	private CrmEventService eventService;
	
	@Autowired
	private CrmCallService callService;
	
	@Autowired
	private CrmMeetingService meetingService;
	
	@Autowired
	private CrmUserService userService;
	
	@Autowired
	private CrmOpportunityTypeService typeService;
	
	@Autowired
	private CrmOpportunityStageService stageService;
	
	@Autowired
	private CrmCustomerService customerService;

	@Autowired
	private CrmCampaignService campaignService;
	
	@Autowired
	private CrmLeadSourceService sourceService;
	
	@Autowired
	private CrmTaskStatusService taskStatusService;
	
	@Autowired
	private CrmCallStatusService callStatusService;
	
	@Autowired
	private CrmMeetingStatusService meetingStatusService;
	
	@Autowired
	private CrmCollaborationService collaborationService;
	
	@Autowired
	private CrmEventLocationService locationService;
	
	@Autowired
	private CrmContactService contactService;
	
	@Autowired
	private CrmOpportunityDetailsService detailsService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@RequestMapping(value="/list_all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listOpportunties(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmOpportunity> arrOpportunities = opService.listOpportunities();
		
		if(arrOpportunities != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrOpportunities);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listOpportuntiesWithSpecificUser(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> arrOpportunities = opService.listOpportunitiesWithSpecificUser(jsonMap.get("username").toString());
		
		if(arrOpportunities != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrOpportunities);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/view", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listInformationRelatedToOpportunity(@RequestBody String json){
		
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = opService.listInformationRelateToOpportunity(jsonMap.get("opId").toString());
		map.put("OPPORTUNITY", opService.findOpportunityById(jsonMap.get("opId").toString()));
		map.put("CALLS", callService.listCallsRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("TASKS", taskService.listTasksRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("MEETINGS",meetingService.listMeetingsRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("NOTES", noteService.listNotesRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("EVENTS", eventService.listEventsRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(jsonMap.get("username").toString()));
		map.put("OPP_TYPES", typeService.listOpportunityTypes());
		map.put("OPP_STAGES", stageService.listOpportunityStages());
		map.put("CUSTOMERS", customerService.listCustomerIdAndName());
		map.put("PRICE_CODE", customerService.listPriceCode());
		map.put("CAMPAIGNS", campaignService.listIdAndNameOfCompaign());
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource());
		map.put("CALL_STATUS", callStatusService.listCallStatus());
		map.put("TASK_STATUS", taskStatusService.lisTaskStatus());
		map.put("MEETING_STATUS", meetingStatusService.listMeetingStatus());
		map.put("EVENT_LOCATION", locationService.listEventLocations());
		map.put("COLLABORATIONS", collaborationService.listCollaborations(jsonMap.get("opId").toString()));
		map.put("TAG_TO", userService.listAllUsernameAndId());
		map.put("ALL_CONTACT", contactService.listSomeFieldsOfContact());
		map.put("OPPORTUNITY_DETAILS", detailsService.listOpportunityDetailsRelatedToOpportunity(jsonMap.get("opId").toString()));
		map.put("OPPORTUNITY_DETAILS_STARTUP", detailsService.startUpPage());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findOpportunityById(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object opportunity = opService.findOpportunityById(opId);
		
		if(opportunity != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", opportunity);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/startup/contact/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listContactsRelatedToOpportuntiy(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> contacts = opService.listContactsRelatedToOpportuntiy(opId);
		
		if(contacts != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("CONTACTS", contacts);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/startup/saleorder/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listSaleOrdersRelatedToOpportuntiy(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> saleorders = opService.listSaleOrdersRelatedToOpportuntiy(opId);
		
		if(saleorders != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("SALE_ORDERS", saleorders);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/startup/quotation/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listQuotationsRelatedToOpportuntiy(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> quotations = opService.listQuotationsRelatedToOpportuntiy(opId);
		
		if(quotations != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("QUOTATIONS", quotations);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/details/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findOpportunityDetailsById(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmOpportunity opportunity = opService.findOpportunityDetailsById(opId);
		
		if(opportunity != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", opportunity);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/add/startup", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addOnStartupPage(@RequestBody String json){
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(jsonMap.get("username").toString()));
		map.put("OPP_TYPES", typeService.listOpportunityTypes());
		map.put("OPP_STAGES", stageService.listOpportunityStages());
		map.put("CUSTOMERS", customerService.listCustomerIdAndName());
		map.put("PRICE_CODE", customerService.listPriceCode());
		map.put("CLASSES", customerService.listAmeClasses());
		map.put("CAMPAIGNS", campaignService.listIdAndNameOfCompaign());
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addNewOpportunity(@RequestBody CrmOpportunity opportunity){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(opService.isInsertOpportunity(opportunity) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit/startup", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> editOnStartupPage(@RequestBody String json){
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(jsonMap.get("username").toString()));
		map.put("OPP_TYPES", typeService.listOpportunityTypes());
		map.put("OPP_STAGES", stageService.listOpportunityStages());
		map.put("CUSTOMERS", customerService.listCustomerIdAndName());
		map.put("PRICE_CODE", customerService.listPriceCode());
		map.put("CLASSES", customerService.listAmeClasses());
		map.put("CAMPAIGNS", campaignService.listIdAndNameOfCompaign());
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource());
		map.put("OPPORTUNITY", opService.findOpportunityById(jsonMap.get("opId").toString()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateOpportunity(@RequestBody CrmOpportunity opportunity){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opService.isUpdateOpportunity(opportunity) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit/custom", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCustomOpportunity(@RequestBody CrmOpportunity opportunity){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opService.updateCustomFieldsOfOpprotunity(opportunity) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove/{opId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteOpportunity(@PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(opService.isDeleteOpportunity(opId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
