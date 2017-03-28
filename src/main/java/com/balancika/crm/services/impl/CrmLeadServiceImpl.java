package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmLeadDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;
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
	public boolean deleteLead(CrmLead lead) {
		return dao.deleteLead(lead);
	}

	@Override
	public List<CrmLead> getAllLead(MeDataSource dataSource) {
		return dao.getAllLead(dataSource);
	}

	@Override
	public Object findLeadById(String leadID, MeDataSource dataSource) {
		return dao.findLeadById(leadID, dataSource);
	}

	@Override
	public CrmLead findLeadDetailById(String leadID, MeDataSource dataSource) {
		return dao.findLeadDetailById(leadID, dataSource);
	}


	@Override
	public List<CrmLead> getLeadBySpecificUser(String username, MeDataSource dataSource) {
		return dao.getLeadBySpecificUser(username, dataSource);
	}

	@Override
	public boolean updateLeadStatusToConverted(String leadID, String custId, String opId, MeDataSource dataSource) {
		return dao.updateLeadStatusToConverted(leadID,custId,opId,dataSource);
	}

	@Override
	public Map<String, Object> viewLeadById(String leadId, String userId, MeDataSource dataSource) {
		return dao.viewLeadById(leadId, userId, dataSource);
	}

	@Override
	public Map<String, Object> convertLeadStartup(String leadId, String userId, MeDataSource dataSource) {
		return dao.convertLeadStartup(leadId, userId, dataSource);
	}

	@Override
	public Map<String, Object> editLeadStartup(String leadId, String userId, MeDataSource dataSource) {
		return dao.editLeadStartup(leadId, userId, dataSource);
	}

	@Override
	public Map<String, Object> createLeadStartup(String userId, MeDataSource dataSource) {
		return dao.createLeadStartup(userId, dataSource);
	}

}
