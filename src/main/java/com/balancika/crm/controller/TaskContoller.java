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
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmTaskService;
import com.balancika.crm.services.CrmUserActivityService;

@RestController
@RequestMapping("/api/task")
public class TaskContoller {
	
	@Autowired
	private  CrmTaskService taskService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivity activity;

	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listTasks(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmTask> arrTask = taskService.listTasks(dataSource);
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
	
	@RequestMapping(value = "/list/{taskId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findTaskById(@PathVariable("taskId") String taskId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object task = taskService.findTaskById(taskId, dataSource);
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
	
	@RequestMapping(value="/list/lead/{leadId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listTaskRelatedToOpportunity(@PathVariable("leadId") String leadId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmTask> tasks = taskService.listTasksRelatedToLead(leadId, dataSource);
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
	
	@RequestMapping(value="/list/opp/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listTaskRelatedToLead(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmTask> note = taskService.listTasksRelatedToOpportunity(opId, dataSource);
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
	
	@RequestMapping(value="/list/module/{moduleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listTaskRelatedToModule(@PathVariable("moduleId") String moduleId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmTask> note = taskService.listTasksRelatedToModule(moduleId, dataSource);
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
	
	@RequestMapping(value = "/list/details/{taskId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findTaskDetailsById(@PathVariable("taskId") String taskId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmTask task = taskService.findTaskDetailsById(taskId, dataSource);
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
			
			map.put("MSG", messageService.getMessage("1000", "task", task.getTaskId(), task.getMeDataSource()));			
			activityService.addUserActivity(activity.getActivity(task.getMeDataSource(), "Create", "task", task.getTaskId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "task", "", task.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateTask(@RequestBody CrmTask task){
		Map<String, Object> map = new HashMap<String, Object>();
		if(taskService.updateTask(task) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1001", "task", task.getTaskId(), task.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(task.getMeDataSource(), "Update", "Task", task.getTaskId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		map.put("MSG", messageService.getMessage("1004", "task", task.getTaskId(), task.getMeDataSource()));
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteTask(@RequestBody CrmTask task){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(taskService.deleteTask(task) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1002", "task", task.getTaskId(), task.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(task.getMeDataSource(), "Delete", "Task", task.getTaskId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FIALED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		map.put("MSG", messageService.getMessage("1005", "task", task.getTaskId(), task.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
