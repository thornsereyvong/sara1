package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.CrmCaseArticle;

public interface CrmCaseArticle {

	boolean insertCaseArticle(CrmCaseArticle article);
	boolean updateCaseArticle(CrmCaseArticle article);
	boolean deleteCaseArticle(CrmCaseArticle article);
	List<CrmCaseArticle> listCasesArticle(MeDataSource dataSource);
	Object findCaseArticleById(String articleId, MeDataSource dataSource);
	CrmCaseArticle findCaseArticleDetailsById(String articleId, MeDataSource dataSource);
}
