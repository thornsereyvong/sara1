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

import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.PriceCode;
import com.balancika.crm.services.CrmAccountTypeService;
import com.balancika.crm.services.CrmCollaborationService;
import com.balancika.crm.services.CrmCustomerService;
import com.balancika.crm.services.CrmIndustryService;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmUserActivityService;
import com.balancika.crm.services.CustomerGroupService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CrmCustomerService customerService;
	
	@Autowired
	private CustomerGroupService groupService;
	
	@Autowired
	private CrmIndustryService industryService;
	
	@Autowired
	private CrmAccountTypeService typeService;
	
	@Autowired
	private CrmCollaborationService collaborationService;

	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listCustomers(@RequestBody MeDataSource datasource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmCustomer> arrCustomer = customerService.listCustomers(datasource);
		
		if(arrCustomer != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrCustomer);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/view/{custId}/{userId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> viewCustomer(@PathVariable("custId") String custId, @PathVariable("userId") String userId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = customerService.viewCustomerById(custId, userId, dataSource);
		map.put("COLLABORATIONS", collaborationService.listCollaborations(custId, dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{custID}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findCustomerById(@PathVariable("custID") String custID, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmCustomer customer = customerService.findCustomerById(custID, dataSource);
		
		if(customer != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", customer);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	@RequestMapping(value="/credit-info/{custID}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> creditInfo(@PathVariable("custID") String custID, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapdata = customerService.creditInfoByCustomer(custID, dataSource);
		
		if(mapdata != null){
			map.put("MESSAGE", "FOUND");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", mapdata);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	@RequestMapping(value="/add/startup", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addStartupPage(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GROUP", groupService.listCustomerGroups(dataSource));
		map.put("PRICE_CODE", customerService.listPriceCode(dataSource));
		map.put("INDUSTRY", industryService.listIndustries(dataSource));
		map.put("TYPE", typeService.listAccountTypes(dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit/startup/{custId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> editStartupPage(@PathVariable("custId") String custId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GROUP", groupService.listCustomerGroups(dataSource));
		map.put("PRICE_CODE", customerService.listPriceCode(dataSource));
		map.put("INDUSTRY", industryService.listIndustries(dataSource));
		map.put("TYPE", typeService.listAccountTypes(dataSource));
		map.put("CUSTOMER", customerService.findCustomerById(custId, dataSource));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody CrmCustomer customer){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(customerService.insertCustomer(customer) == true){
			map.put("MESSAGE", "INSERTED");
			System.out.println(customer.getCustID());
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1000", "customer", customer.getCustID(), customer.getMeDataSource()));			
			activityService.addUserActivity(activity.getActivity(customer.getMeDataSource(), "Create", "Customer", customer.getCustID()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "customer", "", customer.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateCustomer(@RequestBody CrmCustomer customer){		
		Map<String, Object> map = new HashMap<String, Object>();			
		if(customerService.updateCustomer(customer) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1001", "customer", customer.getCustID(), customer.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(customer.getMeDataSource(), "Update", "Customer", customer.getCustID()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "customer", customer.getCustID(), customer.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteCustomer(@RequestBody CrmCustomer customer){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(customerService.deleteCustomer(customer) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1002", "customer", customer.getCustID(), customer.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(customer.getMeDataSource(), "Delete", "Customer", customer.getCustID()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1005", "customer", customer.getCustID(), customer.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/price_code/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listPriceCode(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<PriceCode> priceCodes = customerService.listPriceCode(dataSource);
		
		if(priceCodes != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", priceCodes);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
