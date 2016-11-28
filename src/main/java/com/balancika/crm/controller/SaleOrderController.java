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

import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.SaleOrder;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmUserActivityService;
import com.balancika.crm.services.QuoteService;
import com.balancika.crm.services.SaleOrderService;

@RestController
@RequestMapping("/api/sale_order")
public class SaleOrderController {

	@Autowired
	private SaleOrderService saleOrderService;
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listSaleOrders(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> saleOrders = saleOrderService.listSaleOrders(dataSource);
		if(saleOrders != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			map.put("DATA", saleOrders);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", saleOrders);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{saleId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findSaleOrderByItsId(@PathVariable("saleId") String saleId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		SaleOrder saleOrder = saleOrderService.findSaleOrderById(saleId, dataSource);
		if(saleOrder != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			map.put("DATA", saleOrder);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", saleOrder);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/edit/{saleId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findSaleOrderById(@PathVariable("saleId") String saleId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		SaleOrder saleOrder = saleOrderService.findSaleOrderById(saleId, dataSource);
		if(saleOrder != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			map.put("DATA", saleOrder);
			map.put("SALE_ORDER_STARTUP", quoteService.listQuoteStartupPage(dataSource));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.OK);
		map.put("DATA", saleOrder);
		map.put("SALE_ORDER_STARTUP", "");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addSaleOrder(@RequestBody SaleOrder saleOrder){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(saleOrderService.insertSaleOrder(saleOrder) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED);
			map.put("saleId", saleOrder.getSaleId());
			
			map.put("MSG", messageService.getMessage("1000", "sale order", saleOrder.getSaleId(), saleOrder.getMeDataSource()));			
			activityService.addUserActivity(activity.getActivity(saleOrder.getMeDataSource(), "Create", "Sale Order", saleOrder.getSaleId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		map.put("MSG", messageService.getMessage("1003", "sale order", "", saleOrder.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> editSaleOrder(@RequestBody SaleOrder saleOrder){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(saleOrderService.updateSaleOrder(saleOrder) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK);
			
			map.put("MSG", messageService.getMessage("1001", "sale order", saleOrder.getSaleId(), saleOrder.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(saleOrder.getMeDataSource(), "Update", "Sale Order", saleOrder.getSaleId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		map.put("MSG", messageService.getMessage("1004", "sale order", saleOrder.getSaleId(), saleOrder.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit/post_status/{saleId}/{status}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> editSaleOrderPostStatus(@PathVariable("saleId") String saleId, @PathVariable("status") String status, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		if(saleOrderService.updateSaleOrderPostStatus(saleId, status, dataSource) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteSaleOrder(@RequestBody SaleOrder saleOrder){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(saleOrderService.deleteSaleOrder(saleOrder) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK);
			
			map.put("MSG", messageService.getMessage("1002", "sale order", saleOrder.getSaleId(), saleOrder.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(saleOrder.getMeDataSource(), "Delete", "Sale Order", saleOrder.getSaleId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		map.put("MSG", messageService.getMessage("1005", "sale order", saleOrder.getSaleId(), saleOrder.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/check_entry_no/{saleId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> checkSaleOrderIdExist(@RequestBody MeDataSource dataSource, @PathVariable("saleId") String saleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MESSAGE", saleOrderService.checkSaleOrderIdExist(saleId, dataSource));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
