package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmLeadStatusDao;
import com.balancika.crm.model.CrmLeadStatus;
import com.balancika.crm.services.CrmLeadStatusService;

@Service
@Transactional
public class CrmLeadStatusServiceImpl implements CrmLeadStatusService {

	@Autowired
	private CrmLeadStatusDao statusDao;

	@Override
	public boolean insertLeadStatus(CrmLeadStatus status) {
		return statusDao.insertLeadStatus(status);
	}

	@Override
	public boolean updateLeadStatus(CrmLeadStatus status) {
		return statusDao.updateLeadStatus(status);
	}

	@Override
	public String deleteLeadStatus(int statusID) {
		return statusDao.deleteLeadStatus(statusID);
	}

	@Override
	public List<CrmLeadStatus> getAllLeadStatus() {
		return statusDao.getAllLeadStatus();
	}

	@Override
	public CrmLeadStatus findLeadStatusById(int statusID) {
		return statusDao.findLeadStatusById(statusID);
	}
}
