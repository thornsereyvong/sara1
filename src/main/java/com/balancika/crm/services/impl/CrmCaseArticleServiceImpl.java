package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCaseArticleDao;
import com.balancika.crm.model.CrmCaseArticle;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCaseArticleService;

@Service
@Transactional
public class CrmCaseArticleServiceImpl implements CrmCaseArticleService{

	@Autowired
	private CrmCaseArticleDao articleDao;
	

	@Override
	public boolean insertCaseArticle(CrmCaseArticle article) {
		return articleDao.insertCaseArticle(article);
	}

	@Override
	public boolean updateCaseArticle(CrmCaseArticle article) {
		return articleDao.updateCaseArticle(article);
	}

	@Override
	public boolean deleteCaseArticle(CrmCaseArticle article) {
		return articleDao.deleteCaseArticle(article);
	}

	@Override
	public CrmCaseArticle findCaseArticleDetailsById(String articleId, MeDataSource dataSource) {
		return articleDao.findCaseArticleDetailsById(articleId, dataSource);
	}

	@Override
	public List<CrmCaseArticle> listCasesArticle(MeDataSource dataSource) {
		return articleDao.listCaseArticles(dataSource);
	}

	@Override
	public Object findCaseArticleById(String articleId, MeDataSource dataSource) {
		return articleDao.findCaseArticleById(articleId, dataSource);
	}
}
