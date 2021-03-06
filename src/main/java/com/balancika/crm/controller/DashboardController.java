package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmDashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private CrmDashboardService dashboardService;
	
	@RequestMapping(value ="/view/{username}", method = RequestMethod.POST)
	private ResponseEntity<Map<String, Object>> viewDashboard(@PathVariable("username") String username, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("DASHBOARD", dashboardService.viewDashboard(username, dataSource));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/conf", method = RequestMethod.POST)
	private ResponseEntity<Map<String, Object>> viewDashboard(@RequestBody CrmUser user){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(dashboardService.confDashboard(user) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
	}
	
}
