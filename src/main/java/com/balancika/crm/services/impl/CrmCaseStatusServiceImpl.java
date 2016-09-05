package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCaseStatusDao;
import com.balancika.crm.model.CrmCaseStatus;
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
	public String deleteCaseStatus(int statusId) {
		return statusDao.deleteCaseStatus(statusId);
	}

	@Override
	public List<CrmCaseStatus> listCaseStatus() {
		return statusDao.listCaseStatus();
	}

	@Override
	public CrmCaseStatus findCaseStatusById(int statusId) {
		return statusDao.findCaseStatusById(statusId);
	}

}
