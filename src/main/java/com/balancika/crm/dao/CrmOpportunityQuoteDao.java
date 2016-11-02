package com.balancika.crm.dao;

import com.balancika.crm.model.CrmOpportunityQuotation;
import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunityQuoteDao {

	boolean insertOpportunityQuote(CrmOpportunityQuotation opQuote);
	boolean updateOpportunityQuote(CrmOpportunityQuotation opQuote);
	boolean deleteOpportunityQuote(CrmOpportunityQuotation opQuote);
	CrmOpportunityQuotation findOpportunityQuotationById(int opQuoteId, MeDataSource dataSource);
	Integer checkOpportunityQuotationIsExist(String opId, String quoteId, MeDataSource dataSource);
	Object viewOpportunityQuotationById(int opQuoteId, MeDataSource dataSource);
}
