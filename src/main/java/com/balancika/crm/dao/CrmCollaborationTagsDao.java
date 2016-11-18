package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCollaborationTags;
import com.balancika.crm.model.MeDataSource;

public interface CrmCollaborationTagsDao {
	boolean insertCollaborationTags(List<CrmCollaborationTags> tags, MeDataSource dataSource);
	boolean deleteCollaborationTagsByCollaborationId(int collapId, MeDataSource dataSource);	
	List<CrmCollaborationTags> listCollaborationTags(MeDataSource dataSource);
}
