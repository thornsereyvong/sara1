package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmIndustry;

public interface CrmIndustryDao {

	public boolean insertIndustry(CrmIndustry industry);
	public boolean updateIndustry(CrmIndustry industry);
	public boolean deleteIndustry(int industID);
	public List<CrmIndustry> listIndustries();
	public CrmIndustry finIndustryById(int industID);
}
