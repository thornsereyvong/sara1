package com.balancika.crm.dao;

import com.balancika.crm.model.CrmLike;
import com.balancika.crm.model.MeDataSource;

public interface CrmLikeDao {
	boolean insertLike(CrmLike like);
	boolean deleteLike(int collapId, MeDataSource dataSource);
	Integer countLike(int collapId, MeDataSource dataSource);
	boolean checkUserLike(String username, int postId, MeDataSource dataSource);
}
