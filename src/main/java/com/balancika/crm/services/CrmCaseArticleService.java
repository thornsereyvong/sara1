package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCaseArticle;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseArticleService {

	boolean insertCaseArticle(CrmCaseArticle article);
	boolean updateCaseArticle(CrmCaseArticle article);
	boolean deleteCaseArticle(CrmCaseArticle article);
	List<CrmCaseArticleService> listCasesArticle(MeDataSource dataSource);
	Object findCaseArticleById(String articleId, MeDataSource dataSource);
	CrmCaseArticleService findCaseArticleDetailsById(String articleId, MeDataSource dataSource);
}
