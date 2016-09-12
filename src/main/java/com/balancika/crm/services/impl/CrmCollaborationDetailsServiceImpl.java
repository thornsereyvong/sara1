package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCollaborationDetailsDao;
import com.balancika.crm.model.CrmCollaborationDetails;
import com.balancika.crm.services.CrmCollaborationDetailsService;

@Service
@Transactional
public class CrmCollaborationDetailsServiceImpl implements CrmCollaborationDetailsService{

	@Autowired
	private CrmCollaborationDetailsDao dao;
	
	@Override
	public boolean insertCollaborationDetails(CrmCollaborationDetails details) {
		return dao.insertCollaborationDetails(details);
	}

	@Override
	public boolean updateCollaborationDetails(CrmCollaborationDetails details) {
		return dao.updateCollaborationDetails(details);
	}

	@Override
	public boolean deleteCollaborationDetails(int detailsId) {
		return dao.deleteCollaborationDetails(detailsId);
	}

	@Override
	public List<CrmCollaborationDetails> listCollaborationDetails() {
		return dao.listCollaborationDetails();
	}

	@Override
	public CrmCollaborationDetails findCollaborationDetailsById(int detailsId) {
		return dao.findCollaborationDetailsById(detailsId);
	}

}
