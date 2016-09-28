package com.balancika.crm.controller;

import java.io.IOException;
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

import com.balancika.crm.model.SaleOrder;
import com.balancika.crm.services.QuoteService;
import com.balancika.crm.services.SaleOrderService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/sale_order")
public class SaleOrderController {

	@Autowired
	private SaleOrderService saleOrderService;
	
	@Autowired
	private QuoteService quoteService;
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listSaleOrders(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> saleOrders = saleOrderService.listSaleOrders();
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
	
	@RequestMapping(value="/list/{saleId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findSaleOrderByItsId(@PathVariable("saleId") String saleId){
		Map<String, Object> map = new HashMap<String, Object>();
		SaleOrder saleOrder = saleOrderService.findSaleOrderById(saleId);
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
	
	@RequestMapping(value="/list/edit/{saleId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findSaleOrderById(@PathVariable("saleId") String saleId){
		Map<String, Object> map = new HashMap<String, Object>();
		SaleOrder saleOrder = saleOrderService.findSaleOrderById(saleId);
		if(saleOrder != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK);
			map.put("DATA", saleOrder);
			map.put("SALE_ORDER_STARTUP", quoteService.listQuoteStartupPage());
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
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> editSaleOrder(@RequestBody SaleOrder saleOrder){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(saleOrderService.updateSaleOrder(saleOrder) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit/post_status/{saleId}/{status}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> editSaleOrderPostStatus(@PathVariable("saleId") String saleId, @PathVariable("status") String status){
		Map<String, Object> map = new HashMap<String, Object>();
		if(saleOrderService.updateSaleOrderPostStatus(saleId,status) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove/{saleId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> editSaleOrder(@PathVariable("saleId") String saleId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(saleOrderService.deleteSaleOrder(saleId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/check_entry_no", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> checkSaleOrderIdExist(@RequestBody String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			dataMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MESSAGE", saleOrderService.checkSaleOrderIdExist(dataMap.get("saleId").toString()));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
