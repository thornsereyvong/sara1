package com.balancika.crm.controller;

import java.util.ArrayList;
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

import com.balancika.crm.model.CrmCampaign;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCallService;
import com.balancika.crm.services.CrmCallStatusService;
import com.balancika.crm.services.CrmCampaignService;
import com.balancika.crm.services.CrmCampaignStatusService;
import com.balancika.crm.services.CrmCampaignTypeService;
import com.balancika.crm.services.CrmCollaborationService;
import com.balancika.crm.services.CrmContactService;
import com.balancika.crm.services.CrmEventLocationService;
import com.balancika.crm.services.CrmEventService;
import com.balancika.crm.services.CrmLeadProjectService;
import com.balancika.crm.services.CrmMeetingService;
import com.balancika.crm.services.CrmMeetingStatusService;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmNoteService;
import com.balancika.crm.services.CrmTaskService;
import com.balancika.crm.services.CrmTaskStatusService;
import com.balancika.crm.services.CrmUserActivityService;
import com.balancika.crm.services.CrmUserService;
 
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {
	 
	@Autowired 
	private CrmCampaignService campaignService;
	
	@Autowired
	private CrmCampaignStatusService statusService;
	
	@Autowired
	private CrmCampaignTypeService typeService;
	
	@Autowired
	private CrmUserService userService;
	
	@Autowired
	private CrmCollaborationService collaborationService;
	
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
	private CrmTaskStatusService taskStatusService;
	
	@Autowired
	private CrmCallStatusService callStatusService;
	
	@Autowired
	private CrmMeetingStatusService meetingStatusService;
	
	@Autowired
	private CrmEventLocationService locationService;
	
	@Autowired
	private CrmContactService contactService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@Autowired
	private CrmLeadProjectService projectService;
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> allCampaign(@RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmCampaign> arrCamp = campaignService.listCampaigns(dataSource);
		if( arrCamp == null){
			map.put("MESSAGE", messageService.getMessage("1006", "Campaign", "", dataSource));
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", arrCamp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add/startup/{username}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupAddPage(@RequestBody MeDataSource dataSource, @PathVariable("username") String username){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CAMP_PARENT", campaignService.listCampaignParents(dataSource));
		map.put("CAMP_STATUS", statusService.listAllCampaignStatus(dataSource));
		map.put("CAMP_TYPE", typeService.listAllCampaignType(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("CHILD", userService.checkChildOfUser(username, dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/parent/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listCampaignParents(@RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Object> arrCamp = campaignService.listCampaignParents(dataSource);
		if( arrCamp == null){
			map.put("MESSAGE", messageService.getMessage("1006", "Campaign", "", dataSource));
			map.put("DATA", arrCamp);
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", arrCamp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/view/{campID}/{username}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> viewCampaignById(@PathVariable("campID") String campID, @PathVariable("username") String username, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CAMPAIGN", campaignService.findCampaignById(campID, dataSource));
		map.put("COLLABORATIONS", collaborationService.listCollaborations(campID, dataSource));
		map.put("NOTES", noteService.listNoteRelatedToEachModule(campID, dataSource));
		map.put("TASKS", taskService.listTasksRelatedToModule(campID, dataSource));
		map.put("EVENTS", eventService.listEventsRelatedToModule(campID, dataSource));
		map.put("CALLS", callService.listCallsRelatedToModule(campID, dataSource));
		map.put("MEETINGS", meetingService.listMeetingsRelatedToModule(campID, dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("CAMP_PARENT", campaignService.listCampaignParents(dataSource));
		map.put("CAMP_STATUS", statusService.listAllCampaignStatus(dataSource));
		map.put("CAMP_TYPE", typeService.listAllCampaignType(dataSource));
		map.put("OPPORTUNITIES", campaignService.getOpportunitiesRelatedToCampaign(campID, dataSource));
		map.put("EVENT_LOCATION", locationService.listEventLocations(dataSource));
		map.put("CALL_STATUS", callStatusService.listCallStatus(dataSource));
		map.put("TASK_STATUS", taskStatusService.lisTaskStatus(dataSource));
		map.put("MEETING_STATUS", meetingStatusService.listMeetingStatus(dataSource));
		map.put("TAG_TO", userService.listAllUsernameAndId(dataSource));
		map.put("CONTACTS", contactService.listSomeFieldsOfContact(dataSource));
		map.put("PROJECT",projectService.listLeadProjects(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{campID}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findCampaignById(@PathVariable("campID") String campID, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		Object camp = campaignService.findCampaignById(campID, dataSource);
		if( camp == null){
			map.put("MESSAGE", messageService.getMessage("1006", "Campaign", "", dataSource));
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", camp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/details/{campID}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findCampaignDetailsById(@PathVariable("campID") String campID, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmCampaign camp = campaignService.findCampaignDetailsById(campID, dataSource);
		if( camp == null){
			map.put("MESSAGE", messageService.getMessage("1006", "Campaign", "", dataSource));
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", camp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/not_equal/{campID}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listCampaignIsNotEqual(@PathVariable("campID") String campID, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Object> camp = campaignService.listCampaignIsNotEqual(campID, dataSource);
		if( camp == null){
			map.put("MESSAGE", messageService.getMessage("1006", "Campaign", "", dataSource));
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("DATA", new ArrayList<Object>());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", camp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/validate/{campName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> isCampaignNameExist(@PathVariable("campName") String campName, @RequestBody MeDataSource dataSource){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		if( campaignService.isCampaignNameExist(campName, dataSource) == false){
			map.put("MESSAGE", "NOT_EXIST");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "EXIST");
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addCampaign(@RequestBody CrmCampaign campaign) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(campaignService.insertCampaign(campaign) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1003", "campaign", "", campaign.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		map.put("MESSAGE", "INSERTED");
		map.put("STATUS", HttpStatus.CREATED.value());
		map.put("MSG", messageService.getMessage("1000", "campaign", campaign.getCampID(), campaign.getMeDataSource()));			
		activityService.addUserActivity(activity.getActivity(campaign.getMeDataSource(), "Create", "Campaign", campaign.getCampID()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/edit/startup/{username}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> editCampaignOnStartup(@RequestBody CrmCampaign campaign, @PathVariable("username") String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CAMPAIGN", campaignService.findCampaignById(campaign.getCampID(), campaign.getMeDataSource()));
		map.put("CAMP_STATUS", statusService.listAllCampaignStatus(campaign.getMeDataSource()));
		map.put("CAMP_TYPE", typeService.listAllCampaignType(campaign.getMeDataSource()));
		List<Object> arrCampParent = campaignService.listCampaignIsNotEqual(campaign.getCampID(), campaign.getMeDataSource());
		if(arrCampParent == null){
			map.put("CAMP_PARENT", new ArrayList<Object>());
		}else{
			map.put("CAMP_PARENT", arrCampParent); 
		}
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, campaign.getMeDataSource()));
		map.put("CHILD", userService.checkChildOfUser(username, campaign.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateCampaign(@RequestBody CrmCampaign campaign) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(campaignService.updateCampaign(campaign) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("MSG", messageService.getMessage("1004", "campaign", campaign.getCampID(), campaign.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		map.put("MESSAGE", "UPDATED");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MSG", messageService.getMessage("1001", "campaign", campaign.getCampID(), campaign.getMeDataSource()));
		activityService.addUserActivity(activity.getActivity(campaign.getMeDataSource(), "Update", "Campaign", campaign.getCampID()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteCampaign(@RequestBody CrmCampaign campaign) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(campaignService.deleteCampaign(campaign.getCampID(), campaign.getMeDataSource()).equalsIgnoreCase("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1002", "campaign", campaign.getCampID(), campaign.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(campaign.getMeDataSource(), "Delete", "Campaign", campaign.getCampID()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(campaignService.deleteCampaign(campaign.getCampID(), campaign.getMeDataSource()).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1005", "customer", campaign.getCampID(), campaign.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
