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

import com.balancika.crm.model.CrmContact;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.CrmLeadStatus;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.CrmOpportunityContact;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmAccountTypeService;
import com.balancika.crm.services.CrmCallStatusService;
import com.balancika.crm.services.CrmCampaignService;
import com.balancika.crm.services.CrmCollaborationService;
import com.balancika.crm.services.CrmContactService;
import com.balancika.crm.services.CrmCustomerService;
import com.balancika.crm.services.CrmEventLocationService;
import com.balancika.crm.services.CrmIndustryService;
import com.balancika.crm.services.CrmLeadService;
import com.balancika.crm.services.CrmLeadSourceService;
import com.balancika.crm.services.CrmLeadStatusService;
import com.balancika.crm.services.CrmMeetingStatusService;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmOpportunityContactService;
import com.balancika.crm.services.CrmOpportunityService;
import com.balancika.crm.services.CrmOpportunityStageService;
import com.balancika.crm.services.CrmOpportunityTypeService;
import com.balancika.crm.services.CrmTaskStatusService;
import com.balancika.crm.services.CrmUserActivityService;
import com.balancika.crm.services.CrmUserService;
import com.balancika.crm.services.CustomerGroupService;

@RestController
@RequestMapping("/api/lead")
public class LeadController {
	
	@Autowired
	private CrmLeadService leadService;
	
	@Autowired
	private CrmLeadStatusService leadStatusService;
	
	@Autowired
	private CrmLeadSourceService leadSourceService;
	
	@Autowired
	private CrmCallStatusService callStatusService;
	
	@Autowired
	private CrmMeetingStatusService meetingStatusService;
	
	@Autowired
	private CrmIndustryService industryService;
	
	@Autowired
	private CrmCampaignService campaignService;
	
	@Autowired
	private CrmUserService userService;
	
	@Autowired
	private CrmCustomerService customerService;
	
	@Autowired
	private CrmEventLocationService locationService;
	
	@Autowired
	private CrmContactService contactService;
	
	@Autowired
	private CrmOpportunityService opportunityService;
	
	@Autowired
	private CrmOpportunityTypeService typeService;
	
	@Autowired
	private CrmOpportunityStageService stageService;
	
	@Autowired
	private CrmTaskStatusService taskStatusService;
	
	@Autowired
	private CustomerGroupService groupService;
	
	@Autowired
	private CrmAccountTypeService accountTypeService;
	
	@Autowired
	private CrmCollaborationService collaborationService;
	
	@Autowired
	private CrmOpportunityContactService opContactService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	
	
	@RequestMapping(value = "/list_all", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getAllLead(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmLead> arrLead = leadService.getAllLead(dataSource);
		if(arrLead.isEmpty()){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("DATA", arrLead);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", arrLead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/user/{username}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getLeadBySpecificUser(@RequestBody MeDataSource dataSource, @PathVariable("username") String username){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmLead> arrLead = leadService.getLeadBySpecificUser(username, dataSource);
		
		if(arrLead.isEmpty()){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("DATA", arrLead);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", arrLead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit/startup/{leadId}/{username}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> editLeadOnStartup(@RequestBody MeDataSource dataSource, @PathVariable("leadId") String leadId,  @PathVariable("username") String username){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LEAD", leadService.findLeadById(leadId, dataSource));
		map.put("LEAD_STATUS", leadStatusService.getAllLeadStatus(dataSource));
		map.put("LEAD_SOURCE", leadSourceService.getAllLeadSource(dataSource));
		map.put("INDUSTRY", industryService.listIndustries(dataSource));
		map.put("CAMPAIGN", campaignService.listIdAndNameOfCompaign(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("CHILD", userService.checkChildOfUser(username,dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{leadID}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findLeadById(@PathVariable("leadID") String leadID, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Object Lead = leadService.findLeadById(leadID, dataSource);
		
		if(Lead == null){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", Lead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/{leadId}/{username}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> viewActivitiesOfLeadById(@RequestBody MeDataSource dataSource, @PathVariable("leadId") String leadId, @PathVariable("username") String username){
		
		Map<String, Object> map = leadService.viewActivitiesOfLeadById(leadId, dataSource);
		map.put("LEAD_STATUS", leadStatusService.getAllLeadStatus(dataSource));
		map.put("LEAD_SOURCE", leadSourceService.getAllLeadSource(dataSource));
		map.put("INDUSTRY", industryService.listIndustries(dataSource));
		map.put("CAMPAIGN", campaignService.listIdAndNameOfCompaign(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("CALL_STATUS", callStatusService.listCallStatus(dataSource));
		map.put("MEETING_STATUS", meetingStatusService.listMeetingStatus(dataSource));
		map.put("EVENT_LOCATION", locationService.listEventLocations(dataSource));
		map.put("TASK_STATUS", taskStatusService.lisTaskStatus(dataSource));
		map.put("COLLABORATIONS", collaborationService.listCollaborations(leadId, dataSource));
		map.put("TAG_TO", userService.listAllUsernameAndId(dataSource));
		map.put("CONTACTS", contactService.listSomeFieldsOfContact(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/details/{leadID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findLeadDetailsById(@PathVariable("leadID") String leadID, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmLead Lead = leadService.findLeadDetailById(leadID, dataSource);
		
		if(Lead == null){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
	
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", Lead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add/startup/{username}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addLeadOnStartup(@RequestBody MeDataSource dataSource, @PathVariable("username") String username){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LEAD_STATUS", leadStatusService.getAllLeadStatus(dataSource));
		map.put("LEAD_SOURCE", leadSourceService.getAllLeadSource(dataSource));
		map.put("INDUSTRY", industryService.listIndustries(dataSource));
		map.put("CAMPAIGN", campaignService.listIdAndNameOfCompaign(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("CHILD", userService.checkChildOfUser(username, dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addLead(@RequestBody CrmLead lead){
		
		Map<String , Object> map = new HashMap<String, Object>();
		
		if(leadService.insertLead(lead) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			
			map.put("MSG", messageService.getMessage("1000", "lead", lead.getLeadID(), lead.getMeDataSource()));
			
			activityService.addUserActivity(activity.getActivity(lead.getMeDataSource(), "Create", "Lead", lead.getLeadID()));
			
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1003", "lead", "", lead.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateList(@RequestBody CrmLead lead){
		Map<String, Object> map = new HashMap<String, Object>();
		if(leadService.updateLead(lead) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1001", "lead", lead.getLeadID(), lead.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(lead.getMeDataSource(), "Update", "Lead", lead.getLeadID()));
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1004", "lead", lead.getLeadID(), lead.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/edit/status/{leadId}/{custId}/{opId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateLeadStatusToConverted(
			@RequestBody MeDataSource dataSource, 
			@PathVariable("leadId") String leadId, 
			@PathVariable("custId") String custId, 
			@PathVariable("opId") String opId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(leadService.updateLeadStatusToConverted(leadId,custId,opId, dataSource)== true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/edit/status/{leadId}/{custId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateLeadStatusToConverted(
			@RequestBody MeDataSource dataSource, 
			@PathVariable("leadId") String leadId, 
			@PathVariable("custId") String custId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(leadService.updateLeadStatusToConverted(leadId,custId,null, dataSource)== true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteList(@RequestBody CrmLead lead){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(leadService.deleteLead(lead) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1002", "lead", lead.getLeadID(), lead.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(lead.getMeDataSource(), "Delete", "Lead", lead.getLeadID()));
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1005", "lead", lead.getLeadID(), lead.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/convert/startup/{username}/{leadId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> convertLeadStartup(
			@PathVariable("username") String username, 
			@PathVariable("leadId") String leadId, 
			@RequestBody MeDataSource dataSource){
		
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("GROUP", groupService.listCustomerGroups(dataSource));
		map.put("PRICE_CODE", customerService.listPriceCode(dataSource));
		map.put("CUSTOMER", customerService.listCustomerIdAndName(dataSource));
		map.put("CONTACT", contactService.listContactRelatedToModule(dataSource));
		map.put("LEAD_STATUS", leadStatusService.getAllLeadStatus(dataSource));
		map.put("LEAD_SOURCE", leadSourceService.getAllLeadSource(dataSource));
		map.put("INDUSTRY", industryService.listIndustries(dataSource));
		map.put("CAMPAIGN", campaignService.listIdAndNameOfCompaign(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("OPP_TYPES", typeService.listOpportunityTypes(dataSource));
		map.put("OPP_STAGES", stageService.listOpportunityStages(dataSource));
		map.put("CUSTOMER_TYPE", accountTypeService.listAccountTypes(dataSource));
		map.put("LEAD", leadService.findLeadById(leadId,dataSource));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> convertLead(@RequestBody String json){
		Map<String , Object> map = new HashMap<String, Object>();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String custId = "";
		String contactId = "";
		CrmLeadStatus status = new CrmLeadStatus();
		status.setStatusID(4);
		CrmLead lead = new CrmLead();
		lead.setStatus(status);
		org.codehaus.jackson.map.ObjectMapper objectMapper = new org.codehaus.jackson.map.ObjectMapper();
		String datasourceJson = "";
		MeDataSource dataSource = new MeDataSource();
		try {
			jsonMap = objectMapper.readValue(json, Map.class);
			lead.setLeadID(jsonMap.get("leadID").toString());
			datasourceJson = objectMapper.writeValueAsString(jsonMap.get("DATASOURCE"));
			dataSource = objectMapper.readValue(datasourceJson, MeDataSource.class);
			//System.out.println(jsonMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(jsonMap.get("custID") == ""){
			try {
				String customerJson = objectMapper.writeValueAsString(jsonMap.get("CUSTOMER"));
				CrmCustomer customer = objectMapper.readValue(customerJson, CrmCustomer.class);
				customer.setMeDataSource(dataSource);
				if(customerService.insertCustomer(customer) == true){
					map.put("CUST_MESSAGE", "SUCCESS");
					map.put("CUST_STATUS", HttpStatus.OK.value());
					map.put("CUSTID",customer.getCustID());
					custId = customer.getCustID();
				}else{
					map.put("CUST_MESSAGE", "FAILED");
					map.put("CUST_STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
					custId = jsonMap.get("custID").toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			custId = jsonMap.get("custID").toString();
			map.put("CUST_MESSAGE", "EXIST");
			map.put("CUSTID",custId);
			map.put("OPID", "");
			map.put("CUST_STATUS", HttpStatus.OK.value());
		}
		
		if(jsonMap.get("conID") != ""){
			map.put("CON_MESSAGE", "EXIST");
			map.put("CON_STATUS", HttpStatus.OK.value());
			contactId = jsonMap.get("conID").toString();
		}else if(map.get("CUST_MESSAGE").equals("FAILED")){
			map.put("CUST_MESSAGE", "FAILED");
			map.put("CUST_STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			try {
				String contactJson = objectMapper.writeValueAsString(jsonMap.get("CONTACT"));
				CrmContact contact = objectMapper.readValue(contactJson, CrmContact.class);
				CrmCustomer customer = new CrmCustomer();
				customer.setCustID(custId);
				contact.setCustomer(customer);
				contact.setMeDataSource(dataSource);
				if(contactService.insertContact(contact) == true){
					map.put("CON_MESSAGE", "SUCCESS");
					map.put("CON_STATUS", HttpStatus.OK.value());
					contactId = contact.getConID();
				}else{
					map.put("CON_MESSAGE", "FAILED");
					map.put("CON_STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(map.get("CON_MESSAGE").equals("FAILED")){
			map.put("CON_MESSAGE", "CONVERT CONTACT FAILED");
			map.put("CON_STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else if(jsonMap.get("OPPORTUNITY").equals("")){
			map.put("OP_MESSAGE", "NOT_CREATED");
			map.put("OP_STATUS", HttpStatus.OK.value());
			map.put("OPID", "");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		else{
			try {
				String opportunityJson = objectMapper.writeValueAsString(jsonMap.get("OPPORTUNITY"));
				CrmOpportunity opportunity = objectMapper.readValue(opportunityJson, CrmOpportunity.class);
				CrmCustomer customer = new CrmCustomer();
				customer.setCustID(custId);
				opportunity.setCustomer(customer);
				opportunity.setMeDataSource(dataSource);
				if(opportunityService.isInsertOpportunity(opportunity) == true){
					CrmOpportunityContact opCon = new CrmOpportunityContact();
					opCon.setConId(contactId);
					opCon.setOpId(opportunity.getOpId());
					opCon.setOpConType("Primary");
					opCon.setMeDataSource(opportunity.getMeDataSource());
					opContactService.insterOpportunityContact(opCon);
					map.put("OP_MESSAGE", "SUCCESS");
					map.put("OP_STATUS", HttpStatus.OK.value());
					map.put("OPID", opportunity.getOpId());
					return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
				}else{
					map.put("OP_MESSAGE", "FAILED");
					map.put("OP_STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
					return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MSG", messageService.getMessage("1007", "lead", lead.getLeadID(), dataSource));
		activityService.addUserActivity(activity.getActivity(dataSource, "Convert", "Lead", lead.getLeadID()));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
