package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmLeadProject;
import com.balancika.crm.model.MeDataSource;

public interface CrmLeadProjectDao {
	
	boolean addLeadProject(CrmLeadProject leadProject);
	boolean updateLeadProject(CrmLeadProject leadProject);
	boolean deleteLeadProject(CrmLeadProject leadProject);
	CrmLeadProject findLeadProjectById(int id, MeDataSource dataSource);
	List<CrmLeadProject> listLeadProjects(MeDataSource dataSource);
	List<CrmLeadProject> listCustomFieldLeadProject(MeDataSource dataSource);
}
