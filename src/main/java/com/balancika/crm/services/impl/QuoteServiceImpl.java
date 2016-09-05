package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.QuoteDao;
import com.balancika.crm.model.Quote;
import com.balancika.crm.services.QuoteService;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService{

	@Autowired
	private QuoteDao quoteDao;
	
	@Override
	public List<Object> listQuoteStartupPage() {
		return quoteDao.listQuoteStartupPage();
	}

	@Override
	public Map<String, String> findItemChange(String priceCode, String itemId) {
		return quoteDao.findItemChange(priceCode, itemId);
	}

	@Override
	public Map<String, String> findQuantityAvailable(String itemId, String locationId) {
		return quoteDao.findQuantityAvailable(itemId, locationId);
	}

	@Override
	public boolean insertQuote(Quote quote) {
		return quoteDao.insertQuote(quote);
	}

	@Override
	public boolean updateQuote(Quote quote) {
		return quoteDao.updateQuote(quote);
	}

	@Override
	public boolean deleteQuote(String quoteId) {
		return quoteDao.deleteQuote(quoteId);
	}

	@Override
	public Quote findQuoteById(String quoteId) {
		return quoteDao.findQuoteById(quoteId);
	}

	@Override
	public String checkQuoteIdExist(String quoteId) {
		return quoteDao.checkQuoteIdExist(quoteId);
	}

	@Override
	public List<Quote> listQuotes() {
		return quoteDao.listQuotes();
	}

}
