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
import com.balancika.crm.model.report.CampaingReport;
import com.balancika.crm.services.report.CampaignReportSevice;

@RestController
@RequestMapping("/api/report/campaign")
public class CampaignReportController {

	@Autowired
	private CampaignReportSevice campaignReportSevice;
	
	@RequestMapping(value = "/startup/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> campaignReportStartup(@PathVariable("userId") String userId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = campaignReportSevice.campaignReportStartup(userId, dataSource);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/top-campaign", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> topCampaign(@RequestBody CampaingReport campaingReport){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TOP_CAMPAIGN", campaignReportSevice.reportTopCampaign(campaingReport));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
