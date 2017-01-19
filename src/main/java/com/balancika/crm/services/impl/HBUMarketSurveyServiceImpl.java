package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.HBUMarketSurveyDao;
import com.balancika.crm.model.HBUCustomer;
import com.balancika.crm.model.HBUMarketSurvey;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.HBUMarketSurveyService;

@Service
@Transactional
public class HBUMarketSurveyServiceImpl implements HBUMarketSurveyService{

	@Autowired
	private HBUMarketSurveyDao surveyDao;
	
	@Override
	public boolean InsertMarketSurvey(HBUMarketSurvey survey) {
		return surveyDao.InsertMarketSurvey(survey);
	}

	@Override
	public boolean updateMarketSurvey(HBUMarketSurvey survey) {
		return surveyDao.updateMarketSurvey(survey);
	}

	@Override
	public boolean deleteMakeySurvey(HBUMarketSurvey survey) {
		return surveyDao.deleteMakeySurvey(survey);
	}

	@Override
	public HBUMarketSurvey findMarketSurveyById(String msId, MeDataSource dataSource) {
		return surveyDao.findMarketSurveyById(msId, dataSource);
	}

	@Override
	public Map<String, Object> createMarketSurveyStartup(MeDataSource dataSource) {
		return surveyDao.createMarketSurveyStartup(dataSource);
	}

	@Override
	public Map<String, Object> updateMaketSurveyStartup(String msId, MeDataSource dataSource) {
		return surveyDao.updateMaketSurveyStartup(msId, dataSource);
	}

	@Override
	public List<HBUMarketSurvey> listMarketSurveys(MeDataSource dataSource) {
		return surveyDao.listMarketSurveys(dataSource);
	}

	@Override
	public HBUMarketSurvey findMarketSurveyByItemID(String itemId, MeDataSource dataSource) {
		return surveyDao.findMarketSurveyByItemID(itemId, dataSource);
	}

	@Override
	public List<HBUCustomer> listCustomer(MeDataSource dataSource) {
		return surveyDao.listCustomer(dataSource);
	}

	@Override
	public List<Object> findMarketShare(String msId, MeDataSource dataSource) {
		return surveyDao.findMarketShare(msId, dataSource);
	}
    
	

	
}
