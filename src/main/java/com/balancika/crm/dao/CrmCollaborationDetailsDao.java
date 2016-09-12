package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCollaborationDetails;

public interface CrmCollaborationDetailsDao {
	boolean insertCollaborationDetails(CrmCollaborationDetails details);
	boolean updateCollaborationDetails(CrmCollaborationDetails details);
	boolean deleteCollaborationDetails(int detailsId);
	List<CrmCollaborationDetails> listCollaborationDetails();
	CrmCollaborationDetails findCollaborationDetailsById(int detailsId);
}
