package com.balancika.crm.controller;

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

import com.balancika.crm.model.CrmCall;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCallService;

@RestController
@RequestMapping("/api/call")
public class CallController {

	@Autowired
	private CrmCallService callService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCalls(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCall> arrCall = callService.listCalls(dataSource);
		if(arrCall != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrCall);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", arrCall);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/view/{callId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCallById(@RequestBody MeDataSource dataSource, @PathVariable("callId") String callId){
		Map<String, Object> map = new HashMap<String, Object>();
		Object call = callService.findCallById(callId, dataSource);
		if(call != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", call);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", call);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/lead/{leadId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCallsRelatedToLead(@PathVariable("leadId") String leadId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCall> calls = callService.listCallsRelatedToLead(leadId, dataSource);
		if(calls != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("CALLS", calls);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("CALLS", calls);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/opp/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCallsRelatedToOpportunity(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCall> calls = callService.listCallsRelatedToOpportunity(opId, dataSource);
		if(calls != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("CALLS", calls);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("CALLS", calls);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/module/{moduleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCallsRelatedToCampaign(@PathVariable("moduleId") String moduleId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCall> calls = callService.listCallsRelatedToModule(moduleId, dataSource);
		if(calls != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("CALLS", calls);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("CALLS", calls);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/details/{callId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCallDetailsById(@RequestBody MeDataSource dataSource, @PathVariable("callId") String callId){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCall call = callService.listCallStructureDetailsById(callId, dataSource);
		if(call != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", call);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", call);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCall(@RequestBody CrmCall call){
		Map<String, Object> map = new HashMap<String, Object>();
		if(callService.insertCall(call) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCall(@RequestBody CrmCall call){
		Map<String, Object> map = new HashMap<String, Object>();
		if(callService.updateCall(call) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCall(@RequestBody CrmCall call){
		Map<String, Object> map = new HashMap<String, Object>();
		if(callService.deleteCall(call) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
