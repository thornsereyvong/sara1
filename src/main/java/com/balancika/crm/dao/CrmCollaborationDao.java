package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCollaboration;

public interface CrmCollaborationDao {

	boolean insertCollaboration(CrmCollaboration collaboration);
	boolean updateCollaboration(CrmCollaboration collaboration);
	boolean deleteCollaboration(int colId);
	List<CrmCollaboration> listCollaborations();
}
