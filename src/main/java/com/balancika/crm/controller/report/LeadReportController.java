package com.balancika.crm.controller.report;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.services.report.LeadReportService;

@RestController
@RequestMapping("/api/lead/report")
public class LeadReportController {
	
	@Autowired
	private LeadReportService leadReportService;
	
	@RequestMapping(value = "/exec-lead-trends-by-status", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> reportMarketingExecLeadTrendsByStatus(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT",leadReportService.reportMarketingLeadTrendsByStatus());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exec-lead-by-campaign", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> reportMarketingExecLeadByCampaigns(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT",leadReportService.reportMarketingLeadByCampaigns());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exec-lead-by-industry", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> reportMarketingExecLeadByIndustry(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT",leadReportService.reportMarketingLeadByIndustry());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exec-lead-by-source", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> reportMarketingExecLeadBySource(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT",leadReportService.reportMarketingLeadBySource());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

}
