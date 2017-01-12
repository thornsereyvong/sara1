package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.HBUMarketSurvey;
import com.balancika.crm.model.MeDataSource;

public interface HBUMarketSurveyDao {
	boolean InsertMarketSurvey(HBUMarketSurvey survey);
	boolean updateMarketSurvey(HBUMarketSurvey survey);
	boolean deleteMakeySurvey(HBUMarketSurvey survey);
	HBUMarketSurvey findMarketSurveyById(String msId, MeDataSource dataSource);
	Map<String, Object> createMarketSurveyStartup(MeDataSource dataSource);
	Map<String, Object> updateMaketSurveyStartup(String msId, MeDataSource dataSource);
	List<HBUMarketSurvey> listMarketSurveys(MeDataSource dataSource);
	HBUMarketSurvey findMarketSurveyByItemID(String itemId, MeDataSource dataSource);
}
