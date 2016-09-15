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

import com.balancika.crm.model.CrmCase;
import com.balancika.crm.services.CrmCasePriorityService;
import com.balancika.crm.services.CrmCaseService;
import com.balancika.crm.services.CrmCaseStatusService;
import com.balancika.crm.services.CrmCaseTypeService;
import com.balancika.crm.services.CrmContactService;
import com.balancika.crm.services.CrmCustomerService;
import com.balancika.crm.services.CrmUserService;

@RestController
@RequestMapping("/api/case")
public class CaseController {
	
	@Autowired
	private CrmCaseService caseService;
	
	@Autowired
	private CrmUserService userService;
	
	@Autowired
	private CrmCustomerService customerService;
	
	@Autowired
	private CrmContactService contactService;
	
	@Autowired
	private CrmCaseTypeService typeService;
	
	@Autowired
	private CrmCaseStatusService statusService;
	
	@Autowired
	private CrmCasePriorityService priorityService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCases(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCase> arrCase = caseService.listCases();
		if(arrCase != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrCase);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/list/{caseId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCaseById(@PathVariable("caseId") String caseId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object cases = caseService.findCaseById(caseId);
		if(cases != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", cases);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/startup/{username}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> caseAddStartupPage(@PathVariable("username") String username){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CASE_STATUS", statusService.listCaseStatus());
		map.put("CASE_TYPE", typeService.listCaseTypes());
		map.put("CUSTOMERS", customerService.listCustomerIdAndName());
		map.put("CONTACTS", contactService.listContacts());
		map.put("CASE_PRIORITY", priorityService.listCasePriorities());
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/details/{caseId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCaseDetailsById(@PathVariable("caseId") String caseId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCase cases = caseService.findCaseDetailsById(caseId);
		if(cases != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", cases);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", "");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCases(@RequestBody CrmCase cases){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(caseService.insertCase(cases) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCases(@RequestBody CrmCase cases){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(caseService.updateCase(cases) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/remove/{caseId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCases(@PathVariable("caseId") String caseId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(caseService.deleteCase(caseId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
