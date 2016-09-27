package com.balancika.crm.controller;

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

import com.balancika.crm.model.CrmOpportunitySaleOrder;
import com.balancika.crm.services.CrmOpportunitySaleOrderService;

@RestController
@RequestMapping("/api/opportunity_saleorder")
public class OpportunitySaleOrderController {

	@Autowired
	private CrmOpportunitySaleOrderService opportunitySaleOrderService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addOpportunitySaleOrder(@RequestBody CrmOpportunitySaleOrder opSaleOrder){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunitySaleOrderService.checkOpportunitySaleOrderIsExist(opSaleOrder.getOpId(), opSaleOrder.getSaleId()) > 0){
			map.put("MESSAGE", "EXIST");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}else{
			if(opportunitySaleOrderService.insertOpportunitySaleOrder(opSaleOrder) == true){
				map.put("MESSAGE", "INSERTED");
				map.put("STATUS", HttpStatus.CREATED.value());
				map.put("DATA", opportunitySaleOrderService.viewOpportunitySaleOrder(opSaleOrder.getOpSaleId()));
				return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
			}
			
			map.put("MESSAGE", "FAILED");
			map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateOpportunitySaleOrder(@RequestBody CrmOpportunitySaleOrder opSaleOrder){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunitySaleOrderService.insertOpportunitySaleOrder(opSaleOrder) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove/{opSaleOrderId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteOpportunitySaleOrder(@PathVariable("opSaleOrderId") int opSaleOrderId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(opportunitySaleOrderService.deleteOpportunitySaleOrder(opSaleOrderId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove/{opSaleOrderId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findOpportunitySaleOrderById(@PathVariable("opSaleOrderId") int opSaleOrderId){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmOpportunitySaleOrder opportunitySaleOrder = opportunitySaleOrderService.findOpportunitySaleOrder(opSaleOrderId);
		if(opportunitySaleOrder != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("OPPORTUNITY_SALE_ORDER", opportunitySaleOrder);
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("OPPORTUNITY_SALE_ORDER", opportunitySaleOrder);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
