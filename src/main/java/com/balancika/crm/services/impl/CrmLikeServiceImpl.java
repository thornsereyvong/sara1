package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmLikeDao;
import com.balancika.crm.model.CrmLike;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmLikeService;

@Service
@Transactional
public class CrmLikeServiceImpl implements CrmLikeService{

	@Autowired
	private CrmLikeDao likeDao;
	
	@Override
	public boolean insertLike(CrmLike like) {
		return likeDao.insertLike(like);
	}

	@Override
	public boolean deleteLike(int collapId,MeDataSource dataSource) {
		return likeDao.deleteLike(collapId,dataSource);
	}

	@Override
	public Integer countLike(int collapId, MeDataSource dataSource) {
		return likeDao.countLike(collapId, dataSource);
	}

}
