package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityQuoteDao;
import com.balancika.crm.model.CrmOpportunityQuotation;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityQuoteService;

@Service
@Transactional
public class CrmOpportunityQuoteServiceImpl implements CrmOpportunityQuoteService{

	@Autowired
	private CrmOpportunityQuoteDao opportunityQuoteDao;
	
	@Override
	public boolean insertOpportunityQuote(CrmOpportunityQuotation opQuote) {
		return opportunityQuoteDao.insertOpportunityQuote(opQuote);
	}

	@Override
	public boolean updateOpportunityQuote(CrmOpportunityQuotation opQuote) {
		return opportunityQuoteDao.updateOpportunityQuote(opQuote);
	}

	@Override
	public boolean deleteOpportunityQuote(CrmOpportunityQuotation opQuote) {
		return opportunityQuoteDao.deleteOpportunityQuote(opQuote);
	}

	@Override
	public CrmOpportunityQuotation findOpportunityQuotationById(int opQuoteId, MeDataSource dataSource) {
		return opportunityQuoteDao.findOpportunityQuotationById(opQuoteId, dataSource);
	}

	@Override
	public Integer checkOpportunityQuotationIsExist(String opId, String quoteId, MeDataSource dataSource) {
		return opportunityQuoteDao.checkOpportunityQuotationIsExist(opId, quoteId, dataSource);
	}

	@Override
	public Object viewOpportunityQuotationById(int opQuoteId, MeDataSource dataSource) {
		return opportunityQuoteDao.viewOpportunityQuotationById(opQuoteId, dataSource);
	}

}
