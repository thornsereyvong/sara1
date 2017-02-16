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

import com.balancika.crm.model.report.CustomerReportFilter;
import com.balancika.crm.services.report.CustomerReportService;

@RestController
@RequestMapping("/api/report/customer")
public class CustomerReportController {
	
	@Autowired
	private CustomerReportService reportService;

	@RequestMapping(value = "/top-customer", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> topCustomerReport(@RequestBody CustomerReportFilter filter){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("REPORT", reportService.opportunities(filter));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
