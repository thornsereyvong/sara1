package com.balancika.crm.services.report;

import java.util.List;

import com.balancika.crm.model.report.CustomerReportFilter;
import com.balancika.crm.model.report.OpportunityModel;

public interface CustomerReportService {
	List<OpportunityModel> opportunities(CustomerReportFilter filter);
}
