package com.balancika.crm.services;

import com.balancika.crm.model.CrmLike;

public interface CrmLikeService {
	boolean insertLike(CrmLike like);
	boolean deleteLike(String username);
	Integer countLike(int collapId);
}
