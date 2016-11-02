package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.Quote;

public interface QuoteService {
	List<Object> listQuoteStartupPage(MeDataSource dataSource);
	boolean insertQuote(Quote quote);
	boolean updateQuote(Quote quote);
	boolean deleteQuote(Quote quote);
	Quote findQuoteById(String quoteId, MeDataSource dataSource);
	Map<String, String> findItemChange(String priceCode, String itemId, MeDataSource dataSource); 
	Map<String, String> findQuantityAvailable(String itemId, String locationId, MeDataSource dataSource);
	String checkQuoteIdExist(String quoteId, MeDataSource dataSource);
	List<Quote> listQuotes(MeDataSource dataSource);
}
