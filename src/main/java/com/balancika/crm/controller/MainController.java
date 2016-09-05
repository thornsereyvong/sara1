package com.balancika.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.balancika.crm.model.CrmDatabaseConfiguration;
import com.balancika.crm.services.CrmCampaignService;
import com.balancika.crm.services.CrmDatabaseConfigurationService;

@Controller
public class MainController {
	
	@Autowired
	private CrmCampaignService campaignService;
	
	@Autowired
	private CrmDatabaseConfigurationService configurationService;

	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String indexPage(ModelMap model){
		model.addAttribute("title", "CRM Campaign");
		model.addAttribute("message", "Hello Index Page!");
		return "index";
		
	}
	
	@RequestMapping(value="/api/user/login/web", method = RequestMethod.GET)
	public CrmDatabaseConfiguration getDatabaseConfiguration(@RequestParam(value = "dbId", required = true) String dbId){
		return configurationService.findDatabaseConfigurationById(dbId);
	}
	
}
