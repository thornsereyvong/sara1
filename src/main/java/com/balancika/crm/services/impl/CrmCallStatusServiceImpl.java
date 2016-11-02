package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCallStatusDao;
import com.balancika.crm.model.CrmCallStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCallStatusService;

@Service
@Transactional
public class CrmCallStatusServiceImpl implements CrmCallStatusService{

	@Autowired
	private CrmCallStatusDao statusDao;

	@Override
	public boolean insertCallStatus(CrmCallStatus status) {
		return statusDao.insertCallStatus(status);
	}

	@Override
	public boolean updateCallStatus(CrmCallStatus status) {
		return statusDao.updateCallStatus(status);
	}

	@Override
	public String deleteCallStatus(CrmCallStatus status) {
		return statusDao.deleteCallStatus(status);
	}

	@Override
	public List<CrmCallStatus> listCallStatus(MeDataSource dataSource) {
		return statusDao.listCallStatus(dataSource);
	}

	@Override
	public CrmCallStatus findCallStatusById(CrmCallStatus status) {
		return statusDao.findCallStatusById(status);
	}
}
