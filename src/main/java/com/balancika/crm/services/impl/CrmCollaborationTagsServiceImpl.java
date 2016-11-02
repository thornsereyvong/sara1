package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCollaborationTagsDao;
import com.balancika.crm.model.CrmCollaborationTags;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCollaborationTagsService;

@Service
@Transactional
public class CrmCollaborationTagsServiceImpl implements CrmCollaborationTagsService{

	@Autowired
	private CrmCollaborationTagsDao tagsDao;
	
	@Override
	public boolean insertCollaborationTags(List<CrmCollaborationTags> tags) {
		return tagsDao.insertCollaborationTags(tags);
	}

	@Override
	public boolean deleteCollaborationTagsByCollaborationId(int collapId, MeDataSource dataSource) {
		return tagsDao.deleteCollaborationTagsByCollaborationId(collapId, dataSource);
	}

	@Override
	public List<CrmCollaborationTags> listCollaborationTags(MeDataSource dataSource) {
		return tagsDao.listCollaborationTags(dataSource);
	}

}
