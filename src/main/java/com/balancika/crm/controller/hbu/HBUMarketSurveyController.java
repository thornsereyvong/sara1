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
import com.balancika.crm.model.HBUCustomer;
import com.balancika.crm.model.HBUMarketSurvey;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmUserActivityService;
import com.balancika.crm.services.HBUMarketSurveyService;

@RestController
@RequestMapping("/api/hbu/market-survey")
public class HBUMarketSurveyController {
	
	@Autowired
	private HBUMarketSurveyService surveyService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listMarketSurveys(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<HBUMarketSurvey> surveys = surveyService.listMarketSurveys(dataSource);
		if(surveys != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("SURVEYS", surveys);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "NOT_FOUNT");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addMarketSurvey(@RequestBody HBUMarketSurvey survey){
		Map<String, Object> map = new HashMap<String, Object>();
		if(surveyService.InsertMarketSurvey(survey) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("MSG", messageService.getMessage("1000", "market survey", survey.getMsId(), survey.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(survey.getMeDataSource(), "Create", "Market Survey", survey.getMsId()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "market survey", "", survey.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateMarketSurvey(@RequestBody HBUMarketSurvey survey){
		Map<String, Object> map = new HashMap<String, Object>();
		if(surveyService.updateMarketSurvey(survey) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1001", "market survey", survey.getMsId(), survey.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(survey.getMeDataSource(), "Update", "Market Survey", survey.getMsId()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "market survey", survey.getMsId(), survey.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteMarketSurvey(@RequestBody HBUMarketSurvey survey){
		Map<String, Object> map = new HashMap<String, Object>();
		if(surveyService.deleteMakeySurvey(survey) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1002", "market survey", survey.getMsId(), survey.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(survey.getMeDataSource(), "Delete", "Market Survey", survey.getMsId()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1005", "market survey", survey.getMsId(), survey.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/{msId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findMarketSurveyById(@RequestBody MeDataSource dataSource, @PathVariable("msId") String msId){
		Map<String, Object> map = new HashMap<String, Object>();
		HBUMarketSurvey survey = surveyService.findMarketSurveyById(msId, dataSource);
		List<HBUCustomer> cust = surveyService.listCustomer(dataSource);
		if(survey != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("SURVEY", survey);
			map.put("CUSTOMERS", cust);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "NOT_FOUNT");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/data/{msId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> data(@RequestBody MeDataSource dataSource, @PathVariable("msId") String msId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> survey = surveyService.findMarketShare(msId, dataSource);
		if(survey != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("SURVEY", survey);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "NOT_FOUNT");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("SURVEY", null);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/find/item/{itemId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findMarketSurveyByItemId(@RequestBody MeDataSource dataSource, @PathVariable("itemId") String itemId){
		Map<String, Object> map = new HashMap<String, Object>();
		HBUMarketSurvey survey = surveyService.findMarketSurveyByItemID(itemId, dataSource);
		if(survey != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("SURVEY", survey);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "NOT_FOUNT");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("SURVEY", survey);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> createMarketSurveyStartup(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = surveyService.createMarketSurveyStartup(dataSource);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

}
