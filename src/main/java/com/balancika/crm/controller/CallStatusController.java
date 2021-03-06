package com.balancika.crm.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmCallStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCallStatusService;

@RestController
@RequestMapping("/api/call_status")
public class CallStatusController {

	@Autowired
	private CrmCallStatusService statusService;
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<Map<String, Object>> listCallStatus(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		List<CrmCallStatus> status = statusService.listCallStatus(dataSource);
		if(status != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", status);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/view/{statusId}", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<Map<String, Object>> findCallStatusById(@RequestBody MeDataSource dataSource, @PathVariable("statusId") int statusId){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		CrmCallStatus status = statusService.findCallStatusById(statusId, dataSource);
		if(status != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", status);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<Map<String, Object>> addCallStatus(@RequestBody CrmCallStatus status){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if(statusService.insertCallStatus(status) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<Map<String, Object>> updateCallStatus(@RequestBody CrmCallStatus status){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if(statusService.updateCallStatus(status) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<Map<String, Object>> deleteCallStatus(@RequestBody CrmCallStatus status){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if(statusService.deleteCallStatus(status).equals("OK")){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		if(statusService.deleteCallStatus(status).equals("FOREIGN_KEY_CONSTRAIN")){
			map.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
