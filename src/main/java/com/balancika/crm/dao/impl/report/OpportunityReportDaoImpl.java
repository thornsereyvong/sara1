package com.balancika.crm.dao.impl.report;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCampaignDao;
import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.dao.CrmLeadSourceDao;
import com.balancika.crm.dao.CrmOpportunityStageDao;
import com.balancika.crm.dao.CrmOpportunityTypeDao;
import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.dao.report.OpportunityReportDao;
import com.balancika.crm.model.MeDataSource;

@Repository
public class OpportunityReportDaoImpl implements OpportunityReportDao{
	
	@Autowired
	private CrmOpportunityStageDao stageDao;
	
	@Autowired
	private CrmOpportunityTypeDao typeDao;
	
	@Autowired
	private CrmCampaignDao campaignDao;
	
	@Autowired
	private CrmCustomerDao customerDao;
	
	@Autowired
	private CrmLeadSourceDao sourceDao;
	
	@Autowired
	private CrmUserDao userDao;
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Map<String, Object> startupReport(MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STAGES", stageDao.listOpportunityStages(dataSource));
		map.put("TYPES", typeDao.listOpportunityTypes(dataSource));
		map.put("CAMPAIGNS", campaignDao.listIdAndNameOfCompaign(dataSource));
		map.put("CUSTOMERS", customerDao.listCustomerIdAndName(dataSource));
		map.put("SOURCES", sourceDao.getAllLeadSource(dataSource));
		map.put("ASSIGN_TO", userDao.listSubordinateUserByUsername(dataSource.getUserid(), dataSource));
		map.put("STARTUP_DATE", startupDateReport("createdDate", dataSource));
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> startupDateReport(String dateType, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String sql = "";
			if(dateType.equals("createdDate")){
				sql = "SELECT MIN(DATE(OP_CDate)) startDate, MAX(DATE(OP_CDate)) endDate FROM crm_opportunity; ";
			}else if(dateType.equals("closedDate")){
				sql = "SELECT MIN(DATE(OP_CloseDate)) startDate, MAX(DATE(OP_CloseDate)) endDate FROM crm_opportunity; ";
			}
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return (Map<String, Object>) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}

	@Override
	public Map<String, Object> opportunityReport(OpportunityReportDao filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
