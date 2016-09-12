package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.model.CrmLike;
import com.balancika.crm.services.CrmLikeService;

@Service
@Transactional
public class CrmLikeServiceImpl implements CrmLikeService{

	@Autowired
	private CrmLikeService likeService;
	
	@Override
	public boolean insertLike(CrmLike like) {
		return likeService.insertLike(like);
	}

	@Override
	public boolean deleteLike(String username) {
		return likeService.deleteLike(username);
	}

	@Override
	public Integer countLike(int collapId) {
		return likeService.countLike(collapId);
	}

}
