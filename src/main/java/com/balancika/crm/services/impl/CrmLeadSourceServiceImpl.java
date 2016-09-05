package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmLeadSourceDao;
import com.balancika.crm.model.CrmLeadSource;
import com.balancika.crm.services.CrmLeadSourceService;

@Service
@Transactional
public class CrmLeadSourceServiceImpl implements CrmLeadSourceService{

	@Autowired
	private CrmLeadSourceDao sourceDao;
	
	@Override
	public boolean insertLeadSource(CrmLeadSource source) {
		return sourceDao.insertLeadSource(source);
	}

	@Override
	public boolean updateLeadSource(CrmLeadSource source) {
		return sourceDao.updateLeadSource(source);
	}

	@Override
	public String deleteLeadSource(int sourceID) {
		return sourceDao.deleteLeadSource(sourceID);
	}

	@Override
	public List<CrmLeadSource> getAllLeadSource() {
		return sourceDao.getAllLeadSource();
	}

	@Override
	public CrmLeadSource findLeadSourceById(int sourceID) {
		return sourceDao.findLeadSourceById(sourceID);
	}
}
