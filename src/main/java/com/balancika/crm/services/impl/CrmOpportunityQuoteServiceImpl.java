package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityQuoteDao;
import com.balancika.crm.model.CrmOpportunityQuotation;
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
	public boolean deleteOpportunityQuote(int opQuoteId) {
		return opportunityQuoteDao.deleteOpportunityQuote(opQuoteId);
	}

	@Override
	public CrmOpportunityQuotation findOpportunityQuotationById(int opQuoteId) {
		return opportunityQuoteDao.findOpportunityQuotationById(opQuoteId);
	}

}
