package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCaseDao;
import com.balancika.crm.model.CrmCaseArticle;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCaseArticleService;

@Service
@Transactional
public class CrmCaseArticleServiceImpl implements CrmCaseArticleService{

	@Autowired
	private CrmCaseDao caseDao;
	

	@Override
	public boolean insertCaseArticle(CrmCaseArticle article) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCaseArticle(CrmCaseArticle article) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCaseArticle(CrmCaseArticle article) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CrmCaseArticle findCaseArticleDetailsById(String articleId, MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmCaseArticle> listCasesArticle(MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findCaseArticleById(String articleId, MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}
}
