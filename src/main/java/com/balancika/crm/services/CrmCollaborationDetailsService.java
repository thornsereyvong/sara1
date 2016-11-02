package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCollaborationDetails;
import com.balancika.crm.model.MeDataSource;

public interface CrmCollaborationDetailsService {
	boolean insertCollaborationDetails(CrmCollaborationDetails details);
	boolean updateCollaborationDetails(CrmCollaborationDetails details);
	boolean deleteCollaborationDetails(CrmCollaborationDetails details);
	List<CrmCollaborationDetails> listCollaborationDetails(MeDataSource dataSource);
	CrmCollaborationDetails findCollaborationDetailsById(int detailsId, MeDataSource dataSource);
}
