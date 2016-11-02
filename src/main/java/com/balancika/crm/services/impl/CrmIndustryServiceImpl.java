package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmIndustryDao;
import com.balancika.crm.model.CrmIndustry;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmIndustryService;


@Service
@Transactional
public class CrmIndustryServiceImpl implements CrmIndustryService{

	@Autowired
	private CrmIndustryDao industDao;
	
	@Override
	public boolean insertIndustry(CrmIndustry industry) {
		return industDao.insertIndustry(industry);
	}

	@Override
	public boolean updateIndustry(CrmIndustry industry) {
		return industDao.updateIndustry(industry);
	}

	@Override
	public boolean deleteIndustry(CrmIndustry industry) {
		return industDao.deleteIndustry(industry);
	}

	@Override
	public List<CrmIndustry> listIndustries(MeDataSource dataSource) {
		return industDao.listIndustries(dataSource);
	}

	@Override
	public CrmIndustry finIndustryById(int industID, MeDataSource dataSource) {
		return industDao.finIndustryById(industID, dataSource);
	}
}
