package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmCollaboration;

public interface CrmCollaborationDao {

	boolean insertCollaboration(CrmCollaboration collaboration);
	boolean updateCollaboration(CrmCollaboration collaboration);
	boolean deleteCollaboration(int colId);
	List<CrmCollaboration> listCollaborations(String moduleId);
	CrmCollaboration findCollaborationById(int collapId);
	List<Map<String, Object>> listAllCollaboration(String username, String moduleType, String moduleId);
}
