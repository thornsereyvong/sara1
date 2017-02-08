package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmLeadProject;
import com.balancika.crm.model.MeDataSource;

public interface CrmLeadProjectService {

	boolean addLeadProject(CrmLeadProject leadProject);
	boolean updateLeadProject(CrmLeadProject leadProject);
	boolean deleteLeadProject(CrmLeadProject leadProject);
	CrmLeadProject findLeadProjectById(int id, MeDataSource dataSource);
	List<CrmLeadProject> listLeadProjects(MeDataSource dataSource);
}
