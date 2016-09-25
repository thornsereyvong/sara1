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
import com.balancika.crm.services.CrmAccountTypeService;
import com.balancika.crm.services.CrmCallStatusService;
import com.balancika.crm.services.CrmCampaignService;
import com.balancika.crm.services.CrmContactService;
import com.balancika.crm.services.CrmCustomerService;
import com.balancika.crm.services.CrmEventLocationService;
import com.balancika.crm.services.CrmIndustryService;
import com.balancika.crm.services.CrmLeadService;
import com.balancika.crm.services.CrmLeadSourceService;
import com.balancika.crm.services.CrmLeadStatusService;
import com.balancika.crm.services.CrmMeetingStatusService;
import com.balancika.crm.services.CrmOpportunityService;
import com.balancika.crm.services.CrmOpportunityStageService;
import com.balancika.crm.services.CrmOpportunityTypeService;
import com.balancika.crm.services.CrmTaskStatusService;
import com.balancika.crm.services.CrmUserService;
import com.balancika.crm.services.CustomerGroupService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@RequestMapping(value = "/list_all", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllLead(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmLead> arrLead = leadService.getAllLead();
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
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getLeadBySpecificUser(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> userMap = new HashMap<String, String>();
		try {
			userMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmLead> arrLead = leadService.getLeadBySpecificUser(userMap.get("username").toString());
		
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
	
	@RequestMapping(value = "/edit/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> editLeadOnStartup(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> leadMap = new HashMap<String, String>();
		try {
			leadMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LEAD", leadService.findLeadById(leadMap.get("leadId").toString()));
		map.put("LEAD_STATUS", leadStatusService.getAllLeadStatus());
		map.put("LEAD_SOURCE", leadSourceService.getAllLeadSource());
		map.put("INDUSTRY", industryService.listIndustries());
		map.put("CAMPAIGN", campaignService.listIdAndNameOfCompaign());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(leadMap.get("username").toString()));
		map.put("CHILD", userService.checkChildOfUser(leadMap.get("username").toString()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{leadID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findLeadById(@PathVariable("leadID") String leadID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Object Lead = leadService.findLeadById(leadID);
		
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
	
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> viewActivitiesOfLeadById(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> leadMap = new HashMap<String, String>();
		try {
			leadMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = leadService.viewActivitiesOfLeadById(leadMap.get("leadId").toString());
		map.put("LEAD_STATUS", leadStatusService.getAllLeadStatus());
		map.put("LEAD_SOURCE", leadSourceService.getAllLeadSource());
		map.put("INDUSTRY", industryService.listIndustries());
		map.put("CAMPAIGN", campaignService.listIdAndNameOfCompaign());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(leadMap.get("username").toString()));
		map.put("CALL_STATUS", callStatusService.listCallStatus());
		map.put("MEETING_STATUS", meetingStatusService.listMeetingStatus());
		map.put("EVENT_LOCATION", locationService.listEventLocations());
		map.put("CONTACT", contactService.listContacts()); // wait to edit
		map.put("TASK_STATUS", taskStatusService.lisTaskStatus());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/details/{leadID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findLeadDetailsById(@PathVariable("leadID") String leadID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmLead Lead = leadService.findLeadDetailById(leadID);
		
		if(Lead == null){
			map.put("MESSAGE", "NOT_FOUND");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
	
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", Lead);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addLeadOnStartup(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> leadMap = new HashMap<String, String>();
		try {
			leadMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LEAD_STATUS", leadStatusService.getAllLeadStatus());
		map.put("LEAD_SOURCE", leadSourceService.getAllLeadSource());
		map.put("INDUSTRY", industryService.listIndustries());
		map.put("CAMPAIGN", campaignService.listIdAndNameOfCompaign());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(leadMap.get("username").toString()));
		map.put("CHILD", userService.checkChildOfUser(leadMap.get("username").toString()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addLead(@RequestBody CrmLead lead){
		
		Map<String , Object> map = new HashMap<String, Object>();
		
		if(leadService.insertLead(lead) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateList(@RequestBody CrmLead lead){
		Map<String, Object> map = new HashMap<String, Object>();
		if(leadService.updateLead(lead) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/edit/status", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateLeadStatusToConverted(@RequestBody String json){
		System.err.println(json);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			jsonMap = new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(leadService.updateLeadStatusToConverted(jsonMap.get("leadID").toString())== true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/remove/{leadID}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteList(@PathVariable("leadID") String leadID){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(leadService.deleteLead(leadID) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/convert/startup/{username}/{leadId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> convertLeadStartup(@PathVariable("username") String username, @PathVariable("leadId") String leadId){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("GROUP", groupService.listCustomerGroups());
		map.put("PRICE_CODE", customerService.listPriceCode());
		map.put("CUSTOMER", customerService.listCustomerIdAndName());
		map.put("CONTACT", contactService.listContactRelatedToModule());
		map.put("LEAD_STATUS", leadStatusService.getAllLeadStatus());
		map.put("LEAD_SOURCE", leadSourceService.getAllLeadSource());
		map.put("INDUSTRY", industryService.listIndustries());
		map.put("CAMPAIGN", campaignService.listIdAndNameOfCompaign());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username));
		map.put("OPP_TYPES", typeService.listOpportunityTypes());
		map.put("OPP_STAGES", stageService.listOpportunityStages());
		map.put("CUSTOMER_TYPE", accountTypeService.listAccountTypes());
		map.put("LEAD", leadService.findLeadById(leadId));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> convertLead(@RequestBody String json){
		Map<String , Object> map = new HashMap<String, Object>();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String custId = "";
		CrmLeadStatus status = new CrmLeadStatus();
		status.setStatusID(4);
		CrmLead lead = new CrmLead();
		lead.setStatus(status);
		org.codehaus.jackson.map.ObjectMapper objectMapper = new org.codehaus.jackson.map.ObjectMapper();
		try {
			jsonMap = objectMapper.readValue(json, Map.class);
			lead.setLeadID(jsonMap.get("leadID").toString());
			//System.out.println(jsonMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(jsonMap.get("custID") == ""){
			try {
				String customerJson = objectMapper.writeValueAsString(jsonMap.get("CUSTOMER"));
				CrmCustomer customer = objectMapper.readValue(customerJson, CrmCustomer.class);
				if(customerService.insertCustomer(customer) == true){
					map.put("CUST_MESSAGE", "SUCCESS");
					map.put("CUST_STATUS", HttpStatus.OK.value());
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
			map.put("CUST_MESSAGE", "EXIST");
			map.put("CUST_STATUS", HttpStatus.OK.value());
		}
		
		if(jsonMap.get("conID") != ""){
			map.put("CON_MESSAGE", "EXIST");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else if(map.get("CUST_MESSAGE").equals("FAILED")){
			map.put("CUST_MESSAGE", "FAILED");
			map.put("CUST_STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}else{
			try {
				String contactJson = objectMapper.writeValueAsString(jsonMap.get("CONTACT"));
				CrmContact contact = objectMapper.readValue(contactJson, CrmContact.class);
				if(contactService.insertContact(contact) == true){
					map.put("CON_MESSAGE", "SUCCESS");
					map.put("CON_STATUS", HttpStatus.OK.value());
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
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		else{
			try {
				String opportunityJson = objectMapper.writeValueAsString(jsonMap.get("OPPORTUNITY"));
				CrmOpportunity opportunity = objectMapper.readValue(opportunityJson, CrmOpportunity.class);
				CrmCustomer customer = new CrmCustomer();
				customer.setCustID(custId);
				opportunity.setCustomer(customer);
				if(opportunityService.isInsertOpportunity(opportunity) == true){
					map.put("OP_MESSAGE", "SUCCESS");
					map.put("OP_STATUS", HttpStatus.OK.value());
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
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
