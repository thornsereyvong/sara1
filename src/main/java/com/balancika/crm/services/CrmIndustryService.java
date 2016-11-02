package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmIndustry;
import com.balancika.crm.model.MeDataSource;

public interface CrmIndustryService {
	public boolean insertIndustry(CrmIndustry industry);
	public boolean updateIndustry(CrmIndustry industry);
	public boolean deleteIndustry(CrmIndustry industry);
	public List<CrmIndustry> listIndustries(MeDataSource dataSource);
	public CrmIndustry finIndustryById(int industID, MeDataSource dataSource);
}
