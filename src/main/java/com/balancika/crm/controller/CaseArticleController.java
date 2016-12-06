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

import com.balancika.crm.model.CrmCaseArticle;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCaseArticleService;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmUserActivityService;

@RestController
@RequestMapping("/api/case-article")
public class CaseArticleController {
	
	@Autowired
	private CrmCaseArticleService caseService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCaseArticles(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCaseArticle> arrCase = caseService.listCasesArticle(dataSource);
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
	
	@RequestMapping(value = "/list/{articleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCaseById(@PathVariable("articleId") String articleId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object cases = caseService.findCaseArticleById(articleId, dataSource);
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
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCases(@RequestBody CrmCaseArticle article){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(caseService.insertCaseArticle(article) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			
			map.put("MSG", messageService.getMessage("1000", "article", article.getArticleId(), article.getMeDataSource()));			
			activityService.addUserActivity(activity.getActivity(article.getMeDataSource(), "Create", "Article", article.getArticleId()));
			
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "article", "", article.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCases(@RequestBody CrmCaseArticle article){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(caseService.updateCaseArticle(article)== true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1001", "article", article.getArticleId(), article.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(article.getMeDataSource(), "Update", "Article", article.getArticleId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		map.put("MSG", messageService.getMessage("1004", "article", article.getArticleId(), article.getMeDataSource()));
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCases(@RequestBody CrmCaseArticle article){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(caseService.deleteCaseArticle(article) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1002", "article", article.getArticleId(), article.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(article.getMeDataSource(), "Delete", "Article", article.getArticleId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		map.put("MSG", messageService.getMessage("1005", "article", article.getArticleId(), article.getMeDataSource()));
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

}
