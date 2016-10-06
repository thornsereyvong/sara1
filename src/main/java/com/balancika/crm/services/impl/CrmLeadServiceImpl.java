package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

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
	public List<CrmLead> getAllLead() {
		return dao.getAllLead();
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

	@Override
	public List<CrmLead> getLeadBySpecificUser(String username) {
		return dao.getLeadBySpecificUser(username);
	}

	@Override
	public Map<String, Object> viewActivitiesOfLeadById(String leadId) {
		return dao.viewActivitiesOfLeadById(leadId);
	}

	@Override
	public boolean updateLeadStatusToConverted(String leadID, String custId, String opId) {
		return dao.updateLeadStatusToConverted(leadID,custId,opId);
	}

}
