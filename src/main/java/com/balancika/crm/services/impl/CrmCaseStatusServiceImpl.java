package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCaseStatusDao;
import com.balancika.crm.model.CrmCaseStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCaseStatusService;

@Service
@Transactional
public class CrmCaseStatusServiceImpl implements CrmCaseStatusService{

	@Autowired
	private CrmCaseStatusDao statusDao;
	
	@Override
	public boolean insertCaseStatus(CrmCaseStatus status) {
		return statusDao.insertCaseStatus(status);
	}

	@Override
	public boolean updateCaseStatus(CrmCaseStatus status) {
		return statusDao.updateCaseStatus(status);
	}

	@Override
	public String deleteCaseStatus(CrmCaseStatus status) {
		return statusDao.deleteCaseStatus(status);
	}

	@Override
	public List<CrmCaseStatus> listCaseStatus(MeDataSource dataSource) {
		return statusDao.listCaseStatus(dataSource);
	}

	@Override
	public CrmCaseStatus findCaseStatusById(int statusId, MeDataSource dataSource) {
		return statusDao.findCaseStatusById(statusId,dataSource);
	}

}
