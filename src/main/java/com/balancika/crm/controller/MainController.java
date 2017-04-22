package com.balancika.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	

	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String indexPage(ModelMap model){
		model.addAttribute("title", "CRM Campaign");
		model.addAttribute("message", "Hello Index Page!");
		return "index";
		
	}
	
	/*@RequestMapping(value="/api/user/login/web", method = RequestMethod.GET)
	public CrmDatabaseConfiguration getDatabaseConfiguration(@RequestParam(value = "dbId", required = true) String dbId){
		return configurationService.findDatabaseConfigurationById(dbId);
	}*/
	
}
