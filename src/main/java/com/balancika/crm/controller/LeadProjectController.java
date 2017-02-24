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

import com.balancika.crm.model.CrmLeadProject;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmLeadProjectService;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmUserActivityService;

@RestController
@RequestMapping("/api/project/")
public class LeadProjectController {

	@Autowired
	private CrmLeadProjectService projectService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addLeadProject(@RequestBody CrmLeadProject leadProject){
		Map<String, Object> map = new HashMap<String, Object>();
		if(projectService.addLeadProject(leadProject) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("MSG", messageService.getMessage("1000", "Lead Project", leadProject.getId()+"", leadProject.getDataSource()));
			activityService.addUserActivity(activity.getActivity(leadProject.getDataSource(), "Create", "Lead Project", leadProject.getId()+""));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "Lead Project", "", leadProject.getDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateLeadProject(@RequestBody CrmLeadProject leadProject){
		Map<String, Object> map = new HashMap<String, Object>();
		if(projectService.updateLeadProject(leadProject) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1001", "Lead Project", leadProject.getId()+"", leadProject.getDataSource()));
			activityService.addUserActivity(activity.getActivity(leadProject.getDataSource(), "Create", "Lead Project", leadProject.getId()+""));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "Lead Project", "", leadProject.getDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteLeadProject(@RequestBody CrmLeadProject leadProject){
		Map<String, Object> map = new HashMap<String, Object>();
		if(projectService.deleteLeadProject(leadProject) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1002", "Lead Project", leadProject.getId()+"", leadProject.getDataSource()));
			activityService.addUserActivity(activity.getActivity(leadProject.getDataSource(), "Create", "Lead Project", leadProject.getId()+""));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1005", "Lead Project", "", leadProject.getDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/view/{id}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findLeadProjectById(@PathVariable("id") int id, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmLeadProject project = projectService.findLeadProjectById(id, dataSource);
		if(project != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", project);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", project);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listLeadProjects(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmLeadProject> projects = projectService.listLeadProjects(dataSource);
		if(projects != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", projects);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", projects);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
