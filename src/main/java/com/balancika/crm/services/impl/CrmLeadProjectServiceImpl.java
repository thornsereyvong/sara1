package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmLeadProjectDao;
import com.balancika.crm.model.CrmLeadProject;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmLeadProjectService;

@Service
@Transactional
public class CrmLeadProjectServiceImpl implements CrmLeadProjectService{

	@Autowired	private CrmLeadProjectDao projectDao;

	@Override
	public boolean addLeadProject(CrmLeadProject leadProject) {
		return projectDao.addLeadProject(leadProject);
	}

	@Override
	public boolean updateLeadProject(CrmLeadProject leadProject) {
		return projectDao.updateLeadProject(leadProject);
	}

	@Override
	public boolean deleteLeadProject(CrmLeadProject leadProject) {
		return projectDao.deleteLeadProject(leadProject);
	}

	@Override
	public CrmLeadProject findLeadProjectById(int id, MeDataSource dataSource) {
		return projectDao.findLeadProjectById(id, dataSource);
	}

	@Override
	public List<CrmLeadProject> listLeadProjects(MeDataSource dataSource) {
		return projectDao.listLeadProjects(dataSource);
	}
	

}
