package com.balancika.crm.controller;

import java.io.IOException;
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
import com.balancika.crm.services.CrmCampaignService;
import com.balancika.crm.services.CrmCampaignStatusService;
import com.balancika.crm.services.CrmCampaignTypeService;
import com.balancika.crm.services.CrmUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
 
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
	

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> allCampaign(){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CrmCampaign> arrCamp = campaignService.listCampaigns();
		if( arrCamp == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", arrCamp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupAddPage(@RequestBody String json){
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CAMP_PARENT", campaignService.listCampaignParents());
		map.put("CAMP_STATUS", statusService.listAllCampaignStatus());
		map.put("CAMP_TYPE", typeService.listAllCampaignType());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(jsonMap.get("username").toString()));
		map.put("CHILD", userService.checkChildOfUser(jsonMap.get("username").toString()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/parent/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listCampaignParents(){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Object> arrCamp = campaignService.listCampaignParents();
		if( arrCamp == null){
			map.put("MESSAGE", "FAILED");
			map.put("DATA", arrCamp);
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", arrCamp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{campID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findCampaignById(@PathVariable("campID") String campID){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		Object camp = campaignService.findCampaignById(campID);
		if( camp == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", camp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/details/{campID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findCampaignDetailsById(@PathVariable("campID") String campID){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		CrmCampaign camp = campaignService.findCampaignDetailsById(campID);
		if( camp == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
		}
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("DATA", camp);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/not_equal/{campID}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listCampaignIsNotEqual(@PathVariable("campID") String campID){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Object> camp = campaignService.listCampaignIsNotEqual(campID);
		if( camp == null){
			map.put("MESSAGE", "FAILED");
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
	public ResponseEntity<Map<String, Object>> isCampaignNameExist(@PathVariable("campName") String campName){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		if( campaignService.isCampaignNameExist(campName) == false){
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
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		map.put("MESSAGE", "INSERTED");
		map.put("STATUS", HttpStatus.CREATED.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/edit/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> editCampaignOnStartup(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CAMPAIGN", campaignService.findCampaignById(jsonMap.get("campID").toString()));
		map.put("CAMP_STATUS", statusService.listAllCampaignStatus());
		map.put("CAMP_TYPE", typeService.listAllCampaignType());
		List<Object> arrCampParent = campaignService.listCampaignIsNotEqual(jsonMap.get("campID").toString());
		if(arrCampParent == null){
			map.put("CAMP_PARENT", new ArrayList<Object>());
		}else{
			map.put("CAMP_PARENT", arrCampParent);
		}
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(jsonMap.get("username").toString()));
		map.put("CHILD", userService.checkChildOfUser(jsonMap.get("username").toString()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateCampaign(@RequestBody CrmCampaign campaign) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(campaignService.updateCampaign(campaign) == false){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		map.put("MESSAGE", "UPDATED");
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove/{campId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteCampaign(@PathVariable("campId") String campId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(campaignService.deleteCampaign(campId).equalsIgnoreCase("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(campaignService.deleteCampaign(campId).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
