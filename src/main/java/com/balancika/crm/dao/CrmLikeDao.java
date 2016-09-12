package com.balancika.crm.dao;

import com.balancika.crm.model.CrmLike;

public interface CrmLikeDao {
	boolean insertLike(CrmLike like);
	boolean deleteLike(String username);
	Integer countLike(int collapId);
	boolean checkUserLike(String username);
}
