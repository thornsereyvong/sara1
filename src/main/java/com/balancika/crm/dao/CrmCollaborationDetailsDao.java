package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCollaborationDetails;
import com.balancika.crm.model.MeDataSource;

public interface CrmCollaborationDetailsDao {
	boolean insertCollaborationDetails(CrmCollaborationDetails details);
	boolean updateCollaborationDetails(CrmCollaborationDetails details);
	boolean deleteCollaborationDetails(CrmCollaborationDetails details);
	List<CrmCollaborationDetails> listCollaborationDetails(MeDataSource dataSource);
	CrmCollaborationDetails findCollaborationDetailsById(int detailsId, MeDataSource dataSource);
}
