package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCollaborationDao;
import com.balancika.crm.model.CrmCollaboration;
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
	public boolean deleteCollaboration(int colId) {
		return dao.deleteCollaboration(colId);
	}

	@Override
	public List<CrmCollaboration> listCollaborations() {
		return dao.listCollaborations();
	}

	@Override
	public CrmCollaboration findCollaborationById(int collapId) {
		return dao.findCollaborationById(collapId);
	}

}
