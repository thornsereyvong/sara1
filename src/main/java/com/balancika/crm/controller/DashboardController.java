package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.services.CrmDashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private CrmDashboardService dashboardService;
	
	@RequestMapping(value ="/view/{username}", method = RequestMethod.GET)
	private ResponseEntity<Map<String, Object>> viewDashboard(@PathVariable("username") String username){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("DASHBOARD", dashboardService.viewDashboard(username));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}