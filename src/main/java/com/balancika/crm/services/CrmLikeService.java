package com.balancika.crm.services;

import com.balancika.crm.model.CrmLike;
import com.balancika.crm.model.MeDataSource;

public interface CrmLikeService {
	boolean insertLike(CrmLike like);
	boolean deleteLike(int collapId, MeDataSource dataSource);
	Integer countLike(int collapId, MeDataSource dataSource);
}
