package com.balancika.crm.controller.hbu;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.HBUItem;
import com.balancika.crm.model.HBUItemCompetitor;
import com.balancika.crm.model.HBUItemCustomer;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.HBUItemService;

@RestController
@RequestMapping("/api/item")
public class HBUItemController {
	
	@Autowired
	private HBUItemService itemService;
	
	@RequestMapping(value = "/view/{itemId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listCompetitors(@RequestBody MeDataSource dataSource, @PathVariable("itemId") String itemId){
		Map<String, Object> map = new HashMap<String, Object>();
		HBUItem item = itemService.findHBUItemById(itemId, dataSource);
		if(item != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("ITEM", item);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addCompetitorToItem(@RequestBody HBUItemCompetitor itemCompetitor){
		Map<String, Object> map = new HashMap<String, Object>();
		if(itemService.addCompetitorsToItem(itemCompetitor) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("MSG", "");
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", "");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add/customer", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addCustomerToItem(@RequestBody HBUItemCustomer itemCustomer){
		Map<String, Object> map = new HashMap<String, Object>();
		if(itemService.addCustomerOfItem(itemCustomer) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			map.put("MSG", "");
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", "");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateCompetitorToItem(@RequestBody HBUItemCompetitor itemCompetitor){
		Map<String, Object> map = new HashMap<String, Object>();
		if(itemService.addCompetitorsToItem(itemCompetitor) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", "");
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", "");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
