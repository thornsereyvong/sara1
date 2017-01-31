package com.balancika.crm.controller.report;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.CaseReport;
import com.balancika.crm.services.report.CaseReportService;

@RestController
@RequestMapping("/api/report/case")
public class CaseReportController {
	
	@Autowired
	private CaseReportService reportService;

	@RequestMapping(value = "/startup", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> caseReportStartup(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = reportService.caseReportStartup(dataSource);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/case-report", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> caseReport(@RequestBody CaseReport report){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT", reportService.caseReport(report));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
