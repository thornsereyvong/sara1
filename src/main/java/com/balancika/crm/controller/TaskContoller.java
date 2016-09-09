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

import com.balancika.crm.model.CrmTask;
import com.balancika.crm.services.CrmTaskService;

@RestController
@RequestMapping("/api/task")
public class TaskContoller {
	
	@Autowired
	private  CrmTaskService taskService;
	

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listTasks(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmTask> arrTask = taskService.listTasks();
		if(arrTask != null){
			map.put("MESSAGE", "SUCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrTask);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/list/{taskId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findTaskById(@PathVariable("taskId") String taskId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object task = taskService.findTaskById(taskId);
		if(task != null){
			map.put("MESSAGE", "SUCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", task);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", "");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/lead/{leadId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listTaskRelatedToOpportunity(@PathVariable("leadId") String leadId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmTask> tasks = taskService.listTasksRelatedToLead(leadId);
		if(tasks != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("TASKS", tasks);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("TASKS", tasks);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/opp/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listTaskRelatedToLead(@PathVariable("opId") String opId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmTask> note = taskService.listTasksRelatedToOpportunity(opId);
		if(note != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("TASKS", note);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("TASKS", note);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/details/{taskId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findTaskDetailsById(@PathVariable("taskId") String taskId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmTask task = taskService.findTaskDetailsById(taskId);
		if(task != null){
			map.put("MESSAGE", "SUCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", task);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", "");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addTask(@RequestBody CrmTask task){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(taskService.insertTask(task) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateTask(@RequestBody CrmTask task){
		Map<String, Object> map = new HashMap<String, Object>();
		if(taskService.updateTask(task) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/remove/{taskId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable("taskId") String taskId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(taskService.deleteTask(taskId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
