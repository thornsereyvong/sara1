package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.Quote;

public interface QuoteDao {

	List<Object> listQuoteStartupPage();
	boolean insertQuote(Quote quote);
	boolean updateQuote(Quote quote);
	boolean deleteQuote(String quoteId);
	Quote findQuoteById(String quoteId);
	Map<String, String> findItemChange(String priceCode, String itemId); 
	Map<String, String> findQuantityAvailable(String itemId, String locationId);
	String checkQuoteIdExist(String quoteId);
	List<Quote> listQuotes();
	String convertQuoteToSaleOrder();
	List<Quote> listCustomFieldOfQuotes();
}
