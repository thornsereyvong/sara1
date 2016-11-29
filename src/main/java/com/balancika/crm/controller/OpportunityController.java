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

import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
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
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmNoteService;
import com.balancika.crm.services.CrmOpportunityDetailsService;
import com.balancika.crm.services.CrmOpportunityService;
import com.balancika.crm.services.CrmOpportunityStageService;
import com.balancika.crm.services.CrmOpportunityTypeService;
import com.balancika.crm.services.CrmTaskService;
import com.balancika.crm.services.CrmTaskStatusService;
import com.balancika.crm.services.CrmUserActivityService;
import com.balancika.crm.services.CrmUserService;
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
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@RequestMapping(value="/list_all", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listOpportunties(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmOpportunity> arrOpportunities = opService.listOpportunities(dataSource);
		
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
	
	@RequestMapping(value="/list/user/{username}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listOpportuntiesWithSpecificUser(@RequestBody MeDataSource dataSource, @PathVariable("username") String username){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> arrOpportunities = opService.listOpportunitiesWithSpecificUser(username, dataSource);
		
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
	
	@RequestMapping(value="/view/{opId}/{username}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listInformationRelatedToOpportunity(@RequestBody MeDataSource dataSource, @PathVariable("opId") String opId, @PathVariable("username") String username){
		
		Map<String, Object> map = opService.listInformationRelateToOpportunity(opId, dataSource);
		map.put("OPPORTUNITY", opService.findOpportunityById(opId, dataSource));
		map.put("CALLS", callService.listCallsRelatedToOpportunity(opId, dataSource));
		map.put("TASKS", taskService.listTasksRelatedToOpportunity(opId, dataSource));
		map.put("MEETINGS",meetingService.listMeetingsRelatedToOpportunity(opId, dataSource));
		map.put("NOTES", noteService.listNotesRelatedToOpportunity(opId, dataSource));
		map.put("EVENTS", eventService.listEventsRelatedToOpportunity(opId, dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("OPP_TYPES", typeService.listOpportunityTypes(dataSource));
		map.put("OPP_STAGES", stageService.listOpportunityStages(dataSource));
		map.put("CUSTOMERS", customerService.listCustomerIdAndName(dataSource));
		map.put("PRICE_CODE", customerService.listPriceCode(dataSource));
		map.put("CAMPAIGNS", campaignService.listIdAndNameOfCompaign(dataSource));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource(dataSource));
		map.put("CALL_STATUS", callStatusService.listCallStatus(dataSource));
		map.put("TASK_STATUS", taskStatusService.lisTaskStatus(dataSource));
		map.put("MEETING_STATUS", meetingStatusService.listMeetingStatus(dataSource));
		map.put("EVENT_LOCATION", locationService.listEventLocations(dataSource));
		map.put("COLLABORATIONS", collaborationService.listCollaborations(opId, dataSource));
		map.put("TAG_TO", userService.listAllUsernameAndId(dataSource));
		map.put("ALL_CONTACT", contactService.listSomeFieldsOfContact(dataSource));
		map.put("OPPORTUNITY_DETAILS", detailsService.listOpportunityDetailsRelatedToOpportunity(opId, dataSource));
		map.put("OPPORTUNITY_DETAILS_STARTUP", detailsService.startUpPage(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/list/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findOpportunityById(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object opportunity = opService.findOpportunityById(opId, dataSource);
		
		if(opportunity != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", opportunity);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/startup/contact/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listContactsRelatedToOpportuntiy(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> contacts = opService.listContactsRelatedToOpportuntiy(opId, dataSource);
		
		if(contacts != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("CONTACTS", contacts);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/startup/saleorder/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listSaleOrdersRelatedToOpportuntiy(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> saleorders = opService.listSaleOrdersRelatedToOpportuntiy(opId, dataSource);
		
		if(saleorders != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("SALE_ORDERS", saleorders);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/startup/quotation/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listQuotationsRelatedToOpportuntiy(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> quotations = opService.listQuotationsRelatedToOpportuntiy(opId, dataSource);
		
		if(quotations != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("QUOTATIONS", quotations);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/details/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findOpportunityDetailsById(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmOpportunity opportunity = opService.findOpportunityDetailsById(opId, dataSource);
		
		if(opportunity != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", opportunity);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add/startup/{username}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addOnStartupPage(@PathVariable("username") String username, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username,dataSource));
		map.put("OPP_TYPES", typeService.listOpportunityTypes(dataSource));
		map.put("OPP_STAGES", stageService.listOpportunityStages(dataSource));
		map.put("CUSTOMERS", customerService.listCustomerIdAndName(dataSource));
		map.put("PRICE_CODE", customerService.listPriceCode(dataSource));
		map.put("CLASSES", customerService.listAmeClasses(dataSource));
		map.put("CAMPAIGNS", campaignService.listIdAndNameOfCompaign(dataSource));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addNewOpportunity(@RequestBody CrmOpportunity opportunity){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(opService.isInsertOpportunity(opportunity) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			
			map.put("MSG", messageService.getMessage("1000", "opportunity", opportunity.getOpId(), opportunity.getMeDataSource()));
			
			activityService.addUserActivity(activity.getActivity(opportunity.getMeDataSource(), "Create", "Opportunity", opportunity.getOpId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "opportunity", "", opportunity.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit/startup/{opId}/{username}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> editOnStartupPage(@RequestBody MeDataSource dataSource, @PathVariable("username") String username, @PathVariable("opId") String opId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("OPP_TYPES", typeService.listOpportunityTypes(dataSource));
		map.put("OPP_STAGES", stageService.listOpportunityStages(dataSource));
		map.put("CUSTOMERS", customerService.listCustomerIdAndName(dataSource));
		map.put("PRICE_CODE", customerService.listPriceCode(dataSource));
		map.put("CLASSES", customerService.listAmeClasses(dataSource));
		map.put("CAMPAIGNS", campaignService.listIdAndNameOfCompaign(dataSource));
		map.put("LEAD_SOURCE", sourceService.getAllLeadSource(dataSource));
		map.put("OPPORTUNITY", opService.findOpportunityById(opId , dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateOpportunity(@RequestBody CrmOpportunity opportunity){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opService.isUpdateOpportunity(opportunity) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1001", "opportunity", opportunity.getOpId(), opportunity.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(opportunity.getMeDataSource(), "Update", "Opportunity", opportunity.getOpId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "opportunity", opportunity.getOpId(), opportunity.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit/custom", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCustomOpportunity(@RequestBody CrmOpportunity opportunity){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opService.updateCustomFieldsOfOpprotunity(opportunity) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1001", "opportunity", opportunity.getOpId(), opportunity.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(opportunity.getMeDataSource(), "Update", "Opportunity", opportunity.getOpId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "opportunity", opportunity.getOpId(), opportunity.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteOpportunity(@RequestBody CrmOpportunity opportunity){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(opService.isDeleteOpportunity(opportunity) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1002", "opportunity", opportunity.getOpId(), opportunity.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(opportunity.getMeDataSource(), "Delete", "Opportunity", opportunity.getOpId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1005", "opportunity", opportunity.getOpId(), opportunity.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
