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

import com.balancika.crm.model.Quote;
import com.balancika.crm.services.QuoteService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {

	@Autowired
	private QuoteService quoteService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listQuoteStartupPage() {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> arrList = quoteService.listQuoteStartupPage();
		if (arrList != null) {
			map.put("MESSAGE", "SUCCESS");
			map.put("DATA", arrList);
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/list_all", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listQuotes() {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Quote> arrList = quoteService.listQuotes();
		if (arrList != null) {
			map.put("MESSAGE", "SUCCESS");
			map.put("DATA", arrList);
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("DATA", arrList);
		map.put("STATUS", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{quoteId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findQuoteById(@PathVariable("quoteId") String quoteId) {

		Map<String, Object> map = new HashMap<String, Object>();
		Quote quote = quoteService.findQuoteById(quoteId);
		if (quote != null) {
			map.put("MESSAGE", "SUCCESS");
			map.put("DATA", quote);
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("DATA", "");
		map.put("STATUS", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/edit/{quoteId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findEditQuoteById(@PathVariable("quoteId") String quoteId) {

		Map<String, Object> map = new HashMap<String, Object>();
		Quote quote = quoteService.findQuoteById(quoteId);
		if (quote != null) {
			map.put("MESSAGE", "SUCCESS");
			map.put("DATA", quote);
			map.put("QUOTE_STARTUP", quoteService.listQuoteStartupPage());
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("DATA", "");
		map.put("QUOTE_STARTUP","");
		map.put("STATUS", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item_change", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findItemChange(@RequestBody String json) {
		
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
		
		Map<String, String> itemChange = quoteService.findItemChange(dataMap.get("priceCode").toString(), dataMap.get("itemId").toString());	
		Map<String, Object> map = new HashMap<String, Object>();
		if (itemChange != null) {
			map.put("MESSAGE", "SUCCESS");
			map.put("DATA", itemChange);
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("DATA", "");
		map.put("STATUS", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/check_entry_no", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> checkQuoteIdExist(@RequestBody String json) {
		
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
		map.put("MESSAGE", quoteService.checkQuoteIdExist(dataMap.get("quoteId")));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/qty_available", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findQuantityAvailable(@RequestBody String json) {
		
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
		
		Map<String, String> qtyAvailable = quoteService.findQuantityAvailable(dataMap.get("itemId").toString(), dataMap.get("locationId").toString());	
		Map<String, Object> map = new HashMap<String, Object>();
		if (qtyAvailable != null) {
			map.put("MESSAGE", "SUCCESS");
			map.put("DATA", qtyAvailable);
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("DATA", "");
		map.put("STATUS", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addQuote(@RequestBody Quote quote) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (quoteService.insertQuote(quote) == true) {
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED);
			map.put("quoteId", quote.getSaleId());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
		}

		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateQuote(@RequestBody Quote quote) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (quoteService.updateQuote(quote) == true) {
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/remove/{quoteId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteQuote(@PathVariable("quoteId") String quoteId) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (quoteService.deleteQuote(quoteId)== true) {
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
