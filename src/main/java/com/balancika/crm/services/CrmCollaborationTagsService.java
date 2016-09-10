package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCollaborationTags;

public interface CrmCollaborationTagsService {
	boolean insertCollaborationTags(List<CrmCollaborationTags> tags);
	boolean deleteCollaborationTagsByCollaborationId(int collapId);	
	List<CrmCollaborationTags> listCollaborationTags();
}
