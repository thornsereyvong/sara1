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

import com.balancika.crm.model.AmeItem;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.CrmCaseArticle;
import com.balancika.crm.model.CrmCaseSolution;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.AmeItemService;
import com.balancika.crm.services.CrmCaseArticleService;
import com.balancika.crm.services.CrmCaseOriginService;
import com.balancika.crm.services.CrmCasePriorityService;
import com.balancika.crm.services.CrmCaseService;
import com.balancika.crm.services.CrmCaseStatusService;
import com.balancika.crm.services.CrmCaseTypeService;
import com.balancika.crm.services.CrmCollaborationService;
import com.balancika.crm.services.CrmContactService;
import com.balancika.crm.services.CrmCustomerService;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmUserActivityService;
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
	
	@Autowired
	private CrmCollaborationService collaborationService;
	
	@Autowired
	private AmeItemService itemService;
	
	@Autowired
	private CrmCaseOriginService originService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@Autowired
	private CrmCaseArticleService article;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCases(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCase> arrCase = caseService.listCases(dataSource);
		if(arrCase != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrCase);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{caseId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCaseById(@PathVariable("caseId") String caseId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object cases = caseService.findCaseById(caseId, dataSource);
		if(cases != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", cases);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/{caseId}/{userId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> viewCaseById(@PathVariable("caseId") String caseId, @PathVariable("userId") String userId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = caseService.viewCaseById(caseId, userId, dataSource);
		map.put("COLLABORATIONS", collaborationService.listCollaborations(caseId,dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/startup/{username}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> caseAddStartupPage(@PathVariable("username") String username, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CASE_STATUS", statusService.listCaseStatus(dataSource));
		map.put("CASE_TYPE", typeService.listCaseTypes(dataSource));
		map.put("CASE_ORIGIN", originService.listCaseOrigins(dataSource));
		map.put("CUSTOMERS", customerService.listCustomerIdAndName(dataSource));
		map.put("CONTACTS", contactService.listContactRelatedToModule(dataSource));
		map.put("CASE_PRIORITY", priorityService.listCasePriorities(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("ITEMS", itemService.listItems(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/startup/{username}/{caseId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> caseEditStartupPage(@PathVariable("username") String username, @PathVariable("caseId") String caseId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CASE_STATUS", statusService.listCaseStatus(dataSource));
		map.put("CASE_TYPE", typeService.listCaseTypes(dataSource));
		map.put("CASE_ORIGIN", originService.listCaseOrigins(dataSource));
		map.put("CUSTOMERS", customerService.listCustomerIdAndName(dataSource));
		map.put("CONTACTS", contactService.listContactRelatedToModule(dataSource));
		map.put("CASE_PRIORITY", priorityService.listCasePriorities(dataSource));
		map.put("ASSIGN_TO", userService.listSubordinateUserByUsername(username, dataSource));
		map.put("CASE", caseService.findCaseById(caseId, dataSource));
		map.put("ITEMS", itemService.listItems(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/details/{caseId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCaseDetailsById(@PathVariable("caseId") String caseId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCase cases = caseService.findCaseDetailsById(caseId, dataSource);
		if(cases != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", cases);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", "");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCases(@RequestBody CrmCase cases){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(caseService.insertCase(cases) == true){
			activityService.addUserActivity(activity.getActivity(cases.getMeDataSource(), "Create", "Case", cases.getCaseId()));
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("MSG", messageService.getMessage("1000", "case", cases.getCaseId(), cases.getMeDataSource()));
			
			activityService.addUserActivity(activity.getActivity(cases.getMeDataSource(), "Create", "Case", cases.getCaseId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "case", "", cases.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCases(@RequestBody CrmCase cases){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(caseService.updateCase(cases) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1001", "case", cases.getCaseId(), cases.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(cases.getMeDataSource(), "Update", "Case", cases.getCaseId()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "case", cases.getCaseId(), cases.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resolve", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCases(@RequestBody CrmCaseSolution cases){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(cases.isCreateArt()){
			CrmCaseArticle art = new CrmCaseArticle();
			AmeItem item = null;
			
			if(cases.getItemId() != null){
				item = new AmeItem();
				item.setItemId(cases.getItemId());
				art.setItem(item);
			}
			
			art.setArticleCreateBy(cases.getCreateBy());
			art.setArticleKey(cases.getKey());
			art.setArticleDes(cases.getResolution());
			art.setArticleTitle(cases.getTitle());
			art.setMeDataSource(cases.getMeDataSource());
			
			if(article.insertCaseArticle(art)){
				cases.setArticle(art);
				if(caseService.updateCase(cases) == true){
					activityService.addUserActivity(activity.getActivity(cases.getMeDataSource(), "Update", "Case", cases.getCaseId()));
					map.put("MESSAGE", "UPDATED");
					map.put("STATUS", HttpStatus.OK.value());
					map.put("MSG", messageService.getMessage("1001", "case", cases.getCaseId(), cases.getMeDataSource()));
					return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
				}else{
					System.out.println("error--------------------------");
				}
			}
		}else{
			if(caseService.updateCase(cases) == true){
				activityService.addUserActivity(activity.getActivity(cases.getMeDataSource(), "Update", "Case", cases.getCaseId()));
				map.put("MESSAGE", "UPDATED");
				map.put("STATUS", HttpStatus.OK.value());
				map.put("MSG", messageService.getMessage("1001", "case", cases.getCaseId(), cases.getMeDataSource()));
				return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			}
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "case", cases.getCaseId(), cases.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/escalate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> escalate(@RequestBody CrmCaseSolution cases){
		
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("-----------------------"+cases.getAssignTo().getUserID());
		if(caseService.updateCase(cases) == true){
			activityService.addUserActivity(activity.getActivity(cases.getMeDataSource(), "Update", "Case", cases.getCaseId()));
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1001", "case", cases.getCaseId(), cases.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
			
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "case", cases.getCaseId(), cases.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/edit/custom", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCustomFieldOfCase(@RequestBody CrmCase cases){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(caseService.updateCustomFieldOfCase(cases) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCases(@RequestBody CrmCase cases){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(caseService.deleteCase(cases) == true){
			activityService.addUserActivity(activity.getActivity(cases.getMeDataSource(), "Delete", "Case", cases.getCaseId()));
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1002", "case", cases.getCaseId(), cases.getMeDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1005", "case", cases.getCaseId(), cases.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

}
