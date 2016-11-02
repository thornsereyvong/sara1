package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmCollaboration;
import com.balancika.crm.model.MeDataSource;

public interface CrmCollaborationService {
	boolean insertCollaboration(CrmCollaboration collaboration);
	boolean updateCollaboration(CrmCollaboration collaboration);
	boolean deleteCollaboration(CrmCollaboration collaboration);
	List<CrmCollaboration> listCollaborations(String moduleId, MeDataSource dataSource);
	CrmCollaboration findCollaborationById(int collapId, MeDataSource dataSource);
	List<Map<String, Object>> listAllCollaboration(String username, String moduleType, String moduleId , MeDataSource dataSource);
}
