package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCaseArticle;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseArticleDao {

	boolean insertCaseArticle(CrmCaseArticle article);
	boolean updateCaseArticle(CrmCaseArticle article);
	boolean deleteCaseArticle(CrmCaseArticle article);
	List<CrmCaseArticle> listCaseArticles(MeDataSource dataSource);
	Object findCaseArticleById(String articleId, MeDataSource dataSource);
	CrmCaseArticle findCaseArticleDetailsById(String articleId, MeDataSource dataSource);
}
