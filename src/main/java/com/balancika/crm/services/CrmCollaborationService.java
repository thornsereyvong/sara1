package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCollaboration;

public interface CrmCollaborationService {
	boolean insertCollaboration(CrmCollaboration collaboration);
	boolean updateCollaboration(CrmCollaboration collaboration);
	boolean deleteCollaboration(int colId);
	List<CrmCollaboration> listCollaborations();
	CrmCollaboration findCollaborationById(int collapId);
}
