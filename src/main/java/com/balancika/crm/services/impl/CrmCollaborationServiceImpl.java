package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCollaborationDao;
import com.balancika.crm.model.CrmCollaboration;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCollaborationService;

@Service
@Transactional
public class CrmCollaborationServiceImpl implements CrmCollaborationService{

	@Autowired
	private CrmCollaborationDao dao;
	
	@Override
	public boolean insertCollaboration(CrmCollaboration collaboration) {
		return dao.insertCollaboration(collaboration);
	}

	@Override
	public boolean updateCollaboration(CrmCollaboration collaboration) {
		return dao.updateCollaboration(collaboration);
	}

	@Override
	public boolean deleteCollaboration(CrmCollaboration collaboration) {
		return dao.deleteCollaboration(collaboration);
	}

	@Override
	public List<CrmCollaboration> listCollaborations(String moduleId, MeDataSource dataSource) {
		return dao.listCollaborations(moduleId, dataSource);
	}

	@Override
	public CrmCollaboration findCollaborationById(int collapId, MeDataSource dataSource) {
		return dao.findCollaborationById(collapId, dataSource);
	}

	@Override
	public List<Map<String, Object>> listAllCollaboration(String username, String moduleType, String moduleId, MeDataSource dataSource) {
		return dao.listAllCollaboration(username, moduleType, moduleId, dataSource);
	}

}
