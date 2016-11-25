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

import com.balancika.crm.model.CrmTaskStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmTaskStatusService;

@RestController
@RequestMapping("/api/task_status")
public class TaskStatusController {
	
	@Autowired
	private CrmTaskStatusService statusService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listTaskStatus(@RequestBody MeDataSource dataSource){
		Map<String, Object> statusMap = new HashMap<String, Object>();
		List<CrmTaskStatus> arrStatus = statusService.lisTaskStatus(dataSource);
		if(arrStatus != null){
			statusMap.put("MESSAGE", "SUCCESS");
			statusMap.put("STATUS", HttpStatus.OK.value());
			statusMap.put("DATA", arrStatus);
			return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
		}
		
		statusMap.put("MESSAGE", "FAILED");
		statusMap.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{statusId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findTaskStatusById(@PathVariable("statusId") int statusId, @RequestBody MeDataSource dataSource){
		Map<String, Object> statusMap = new HashMap<String, Object>();
		CrmTaskStatus status = statusService.findTaskStatusById(statusId, dataSource);
		if(status != null){
			statusMap.put("MESSAGE", "SUCCESS");
			statusMap.put("STATUS", HttpStatus.OK.value());
			statusMap.put("DATA", status);
			return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
		}
		
		statusMap.put("MESSAGE", "FAILED");
		statusMap.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addTaskStatus(@RequestBody CrmTaskStatus status){
		Map<String, Object> statusMap = new HashMap<String, Object>();
		if(statusService.insertTaskStatus(status) == true){
			statusMap.put("MESSAGE", "INSERTED");
			statusMap.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.CREATED);
		}
		
		statusMap.put("MESSAGE", "FAILED");
		statusMap.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateTaskStatus(@RequestBody CrmTaskStatus status){
		Map<String, Object> statusMap = new HashMap<String, Object>();
		if(statusService.updateTaskStatus(status) == true){
			statusMap.put("MESSAGE", "UPDATED");
			statusMap.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
		}
		
		statusMap.put("MESSAGE", "FAILED");
		statusMap.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteTaskStatus(@RequestBody CrmTaskStatus status){
		Map<String, Object> statusMap = new HashMap<String, Object>();
		if(statusService.deleteTaskStatus(status).equalsIgnoreCase("OK")){
			statusMap.put("MESSAGE", "DELETED");
			statusMap.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
		}
		
		if(statusService.deleteTaskStatus(status).equalsIgnoreCase("FOREIGN_KEY_CONSTRAIN")){
			statusMap.put("MESSAGE", "FOREIGN_KEY_CONSTRAIN");
			statusMap.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
		}
		
		statusMap.put("MESSAGE", "FAILED");
		statusMap.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(statusMap, HttpStatus.OK);
	}
}
