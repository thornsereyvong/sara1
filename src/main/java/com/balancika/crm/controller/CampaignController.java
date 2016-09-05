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

import com.balancika.crm.model.CrmCampaign;
import com.balancika.crm.services.CrmCampaignService;
import com.balancika.crm.services.CrmCampaignStatusService;
import com.balancika.crm.services.CrmCampaignTypeService;
 
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {
	 
	@Autowired 
	private CrmCampaignService campaignService;
	
	@Autowired
	private CrmCampaignStatusService statusService;
	
	@Autowired
	private CrmCampaignTypeService typeService;
	

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
		map.put("CAMP_STATUS", statusService.listAllCampaignStatus());
		map.put("CAMP_TYPE", typeService.listAllCampaignType());
		map.put("CAMP_PARENT", campaignService.listCampaignIsNotEqual(campID));
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
		
		List<CrmCampaign> camp = campaignService.listCampaignIsNotEqual(campID);
		if( camp == null){
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("DATA", "");
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND); 
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
	
	@RequestMapping(value="/add/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> loadOnPageAddCampaign(){
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("MESSAGE", "SUCCESS");
		map.put("STATUS", HttpStatus.OK.value());
		map.put("CAMP_STATUS", statusService.listAllCampaignStatus());
		map.put("CAMP_TYPE", typeService.listAllCampaignType());
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
