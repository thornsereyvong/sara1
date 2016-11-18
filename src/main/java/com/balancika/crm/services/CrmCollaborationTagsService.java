package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCollaborationTags;
import com.balancika.crm.model.MeDataSource;

public interface CrmCollaborationTagsService {
	boolean insertCollaborationTags(List<CrmCollaborationTags> tags,  MeDataSource dataSource);
	boolean deleteCollaborationTagsByCollaborationId(int collapId, MeDataSource dataSource);	
	List<CrmCollaborationTags> listCollaborationTags(MeDataSource dataSource);
}
