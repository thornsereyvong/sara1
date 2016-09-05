package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmLeadDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.services.CrmLeadService;

@Service
@Transactional
public class CrmLeadServiceImpl implements CrmLeadService {

	@Autowired
	private CrmLeadDao dao;

	@Override
	public boolean insertLead(CrmLead lead) {
		return dao.insertLead(lead);
	}

	@Override
	public boolean updateLead(CrmLead lead) {
		return dao.updateLead(lead);
		
	}

	@Override
	public boolean deleteLead(String leadID) {
		return dao.deleteLead(leadID);
	}

	@Override
	public List<CrmLead> getAllLead(String username) {
		return dao.getAllLead(username);
	}

	@Override
	public Object findLeadById(String leadID) {
		return dao.findLeadById(leadID);
	}

	@Override
	public CrmLead findLeadDetailById(String leadID) {
		return dao.findLeadDetailById(leadID);
	}

	@Override
	public boolean convertLead(String json) {
		return dao.convertLead(json);
	}

}