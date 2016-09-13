package com.balancika.crm.services;

import com.balancika.crm.model.CrmLike;

public interface CrmLikeService {
	boolean insertLike(CrmLike like);
	boolean deleteLike(int collapId);
	Integer countLike(int collapId);
}
