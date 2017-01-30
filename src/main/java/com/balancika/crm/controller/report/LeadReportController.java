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
import com.balancika.crm.services.report.LeadReportService;

@RestController
@RequestMapping("/api/report/lead")
public class LeadReportController {
	
	@Autowired
	private LeadReportService leadReportService;
	
	@RequestMapping(value = "/exec-lead-trends-by-status", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> reportMarketingExecLeadTrendsByStatus(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT",leadReportService.reportMarketingLeadTrendsByStatus(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exec-lead-by-campaign", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> reportMarketingExecLeadByCampaigns(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT",leadReportService.reportMarketingLeadByCampaigns(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exec-lead-by-industry", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> reportMarketingExecLeadByIndustry(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT",leadReportService.reportMarketingLeadByIndustry(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exec-lead-by-source", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> reportMarketingExecLeadBySource(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT",leadReportService.reportMarketingLeadBySource(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupReport(@RequestBody MeDataSource dataSource){
		return new ResponseEntity<Map<String,Object>>(leadReportService.startupReportLead(dataSource), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/startup/date/{dateType}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startupDate(@PathVariable("dateType") String dateType, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STARTUP_DATE", leadReportService.startupDate(dateType, dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

}
