package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmCollaboration;

public interface CrmCollaborationService {
	boolean insertCollaboration(CrmCollaboration collaboration);
	boolean updateCollaboration(CrmCollaboration collaboration);
	boolean deleteCollaboration(int colId);
	List<CrmCollaboration> listCollaborations(String moduleId);
	CrmCollaboration findCollaborationById(int collapId);
	List<Map<String, Object>> listAllCollaboration(String username, String moduleType, String moduleId);
}
