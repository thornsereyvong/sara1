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

import com.balancika.crm.model.CrmOpportunityQuotation;
import com.balancika.crm.services.CrmOpportunityQuoteService;

@RestController
@RequestMapping("/api/opportunity_quote")
public class OpportunityQuoteController {
	
	@Autowired
	private CrmOpportunityQuoteService opportunityQuoteService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addOpportunityQuote(@RequestBody CrmOpportunityQuotation opQuote){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityQuoteService.checkOpportunityQuotationIsExist(opQuote.getOpId(), opQuote.getQuoteId()) > 0){
			map.put("MESSAGE", "EXIST");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}else{
			if(opportunityQuoteService.insertOpportunityQuote(opQuote) == true){
				map.put("MESSAGE", "INSERTED");
				map.put("STATUS", HttpStatus.CREATED.value());
				map.put("DATA", opportunityQuoteService.viewOpportunityQuotationById(opQuote.getOpQuoteId()));
				return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
			}
			
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateOpportunityQuote(@RequestBody CrmOpportunityQuotation opQuote){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityQuoteService.updateOpportunityQuote(opQuote) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove/{opQuoteId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteOpportunityContact(@PathVariable("opQuoteId") int opQuoteId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunityQuoteService.deleteOpportunityQuote(opQuoteId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/{opQuoteId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> viewOpportunityQuote(@PathVariable("opQuoteId") int opQuoteId){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmOpportunityQuotation opportunityQuote = opportunityQuoteService.findOpportunityQuotationById(opQuoteId);
		if(opportunityQuote != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("OPPORTUNITY_QUOTE", opportunityQuote);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("OPPORTUNITY_CONTACT", opportunityQuote);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
