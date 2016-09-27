package com.balancika.crm.dao;

import com.balancika.crm.model.CrmOpportunityQuotation;

public interface CrmOpportunityQuoteDao {

	boolean insertOpportunityQuote(CrmOpportunityQuotation opQuote);
	boolean updateOpportunityQuote(CrmOpportunityQuotation opQuote);
	boolean deleteOpportunityQuote(int opQuoteId);
	CrmOpportunityQuotation findOpportunityQuotationById(int opQuoteId);
	Integer checkOpportunityQuotationIsExist(String opId, String quoteId);
	Object viewOpportunityQuotationById(int opQuoteId);
}
