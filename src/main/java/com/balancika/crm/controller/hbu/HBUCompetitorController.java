package com.balancika.crm.controller.hbu;

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

import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.HBUCompetitor;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.AmeItemService;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmUserActivityService;
import com.balancika.crm.services.HBUCompetitorService;

@RestController
@RequestMapping("/api/hbu/competitor")
public class HBUCompetitorController {
	
	@Autowired
	private HBUCompetitorService competitorService;
	
	@Autowired
	private AmeItemService itemService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listCompetitors(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<HBUCompetitor> competitors = competitorService.listCompetitors(dataSource);
		if(competitors != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("COMPETITORS", competitors);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/{comId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findCompetitorById(@RequestBody MeDataSource dataSource, @PathVariable("comId") String comId){
		Map<String, Object> map = new HashMap<String, Object>();
		HBUCompetitor competitor = competitorService.findCompetitorById(comId, dataSource);
		if(competitor != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("COMPETITOR", competitor);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupAddCompetitor(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ITEMS", itemService.listItems(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/startup/{comId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupUpdateCompetitor(@RequestBody MeDataSource dataSource, @PathVariable("comId") String comId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("COMPETITOR", competitorService.findCompetitorById(comId, dataSource));
		map.put("ITEMS", itemService.listItems(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addCompetitor(@RequestBody HBUCompetitor competitor){
		Map<String, Object> map = new HashMap<String, Object>();
		if(competitorService.insertCompetitor(competitor) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("MSG", messageService.getMessage("1000", "Competitor", competitor.getComId(), competitor.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(competitor.getMeDataSource(), "Create", "Competitor", competitor.getComId()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "Competitor", "", competitor.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateCompetitor(@RequestBody HBUCompetitor competitor){
		Map<String, Object> map = new HashMap<String, Object>();
		if(competitorService.updateCompetitor(competitor) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1001", "Competitor", competitor.getComId(), competitor.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(competitor.getMeDataSource(), "Update", "Competitor", competitor.getComId()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "Competitor", competitor.getComId(), competitor.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteCompetitor(@RequestBody HBUCompetitor competitor){
		Map<String, Object> map = new HashMap<String, Object>();
		if(competitorService.deleteCompetitor(competitor) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1002", "Competitor", competitor.getComId(), competitor.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(competitor.getMeDataSource(), "Delete", "Competitor", competitor.getComId()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1005", "Competitor", competitor.getComId(), competitor.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
