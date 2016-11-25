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

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.Quote;
import com.balancika.crm.services.QuoteService;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {

	@Autowired
	private QuoteService quoteService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listQuoteStartupPage(@RequestBody MeDataSource dataSource) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> arrList = quoteService.listQuoteStartupPage(dataSource);
		if (arrList != null) {
			map.put("MESSAGE", "SUCCESS");
			map.put("DATA", arrList);
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list_all", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listQuotes(@RequestBody MeDataSource dataSource) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Quote> arrList = quoteService.listQuotes(dataSource);
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
	
	@RequestMapping(value = "/list/{quoteId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findQuoteById(@PathVariable("quoteId") String quoteId, @RequestBody MeDataSource dataSource) {

		Map<String, Object> map = new HashMap<String, Object>();
		Quote quote = quoteService.findQuoteById(quoteId, dataSource);
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
	
	@RequestMapping(value = "/list/edit/{quoteId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findEditQuoteById(@PathVariable("quoteId") String quoteId, @RequestBody MeDataSource dataSource) {

		Map<String, Object> map = new HashMap<String, Object>();
		Quote quote = quoteService.findQuoteById(quoteId, dataSource);
		if (quote != null) {
			map.put("MESSAGE", "SUCCESS");
			map.put("DATA", quote);
			map.put("QUOTE_STARTUP", quoteService.listQuoteStartupPage(dataSource));
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("DATA", "");
		map.put("QUOTE_STARTUP","");
		map.put("STATUS", HttpStatus.OK);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item_change/{priceCode}/{itemId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findItemChange(@RequestBody MeDataSource dataSource, @PathVariable("priceCode") String priceCode, @PathVariable("itemId") String itemId) {
		Map<String, String> itemChange = quoteService.findItemChange(priceCode, itemId , dataSource);	
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
	
	@RequestMapping(value = "/check_entry_no/{quoteId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> checkQuoteIdExist(@RequestBody MeDataSource dataSource, @PathVariable("quoteId") String quoteId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MESSAGE", quoteService.checkQuoteIdExist(quoteId, dataSource));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/qty_available/{itemId}/{locationId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findQuantityAvailable(@RequestBody MeDataSource dataSource, @PathVariable("itemId") String itemId, @PathVariable("locationId") String locationId) {
		Map<String, String> qtyAvailable = quoteService.findQuantityAvailable(itemId , locationId, dataSource);	
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
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateQuote(@RequestBody Quote quote) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (quoteService.updateQuote(quote) == true) {
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteQuote(@RequestBody Quote quote) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (quoteService.deleteQuote(quote)== true) {
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
