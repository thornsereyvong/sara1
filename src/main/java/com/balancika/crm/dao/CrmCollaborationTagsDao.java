package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCollaborationTags;

public interface CrmCollaborationTagsDao {
	boolean insertCollaborationTags(List<CrmCollaborationTags> tags);
	boolean deleteCollaborationTagsByCollaborationId(int collapId);	
	List<CrmCollaborationTags> listCollaborationTags();
}
