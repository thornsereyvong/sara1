package com.balancika.crm.services.impl.report;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.report.CustomerReportDao;
import com.balancika.crm.model.report.CustomerReportFilter;
import com.balancika.crm.model.report.OpportunityModel;
import com.balancika.crm.services.report.CustomerReportService;

@Service
@Transactional
public class CustomerReportServiceImpl implements CustomerReportService{
	
	@Autowired
	private CustomerReportDao reportDao;

	@Override
	public List<OpportunityModel> opportunities(CustomerReportFilter filter) {
		return reportDao.opportunities(filter);
	}

}
