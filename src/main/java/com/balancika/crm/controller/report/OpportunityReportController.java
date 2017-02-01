package com.balancika.crm.controller.report;

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

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.report.OpportunityReportService;

@RestController
@RequestMapping("/api/report/opportunity")
public class OpportunityReportController {

	@Autowired
	private OpportunityReportService reportService;
	
	@RequestMapping(value = "/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupReport(@RequestBody MeDataSource dataSource){
		return new ResponseEntity<Map<String,Object>>(reportService.startupReport(dataSource), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/startup/date/{dateType}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupDate(@PathVariable("dateType") String dateType, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STARTUP_DATE", reportService.startupDateReport(dateType, dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
