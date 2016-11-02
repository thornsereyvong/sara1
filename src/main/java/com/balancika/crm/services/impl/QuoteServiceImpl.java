package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.QuoteDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.Quote;
import com.balancika.crm.services.QuoteService;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService{

	@Autowired
	private QuoteDao quoteDao;
	
	@Override
	public List<Object> listQuoteStartupPage(MeDataSource dataSource) {
		return quoteDao.listQuoteStartupPage(dataSource);
	}

	@Override
	public Map<String, String> findItemChange(String priceCode, String itemId, MeDataSource dataSource) {
		return quoteDao.findItemChange(priceCode, itemId, dataSource);
	}

	@Override
	public Map<String, String> findQuantityAvailable(String itemId, String locationId, MeDataSource dataSource) {
		return quoteDao.findQuantityAvailable(itemId, locationId, dataSource);
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
	public boolean deleteQuote(Quote quote) {
		return quoteDao.deleteQuote(quote);
	}

	@Override
	public Quote findQuoteById(String quoteId, MeDataSource dataSource) {
		return quoteDao.findQuoteById(quoteId, dataSource);
	}

	@Override
	public String checkQuoteIdExist(String quoteId, MeDataSource dataSource) {
		return quoteDao.checkQuoteIdExist(quoteId, dataSource);
	}

	@Override
	public List<Quote> listQuotes(MeDataSource dataSource) {
		return quoteDao.listQuotes(dataSource);
	}

}
