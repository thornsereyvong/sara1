package com.balancika.crm.dao.report;

import java.util.List;

import com.balancika.crm.model.report.CustomerReportFilter;
import com.balancika.crm.model.report.OpportunityModel;

public interface CustomerReportDao {
	List<OpportunityModel> opportunities(CustomerReportFilter filter);
}
