package com.balancika.crm.dao.impl.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.balancika.crm.model.report.OpportunityReport;

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
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return new HashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> opportunityReport(OpportunityReport filter) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(filter.getDataSource()));
		Session session  = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String createdDate = "";
			String closedDate = "";
			String stage = "";
			String type = "";
			String source = "";
			String campaign = "";
			String customer = "";
			String assignTo = "";
			
			if(filter.getDateType().equals("createdDate"))
				createdDate = "(DATE(OP_CDate) BETWEEN '"+filter.getStartDate()+"' AND '"+filter.getEndDate()+"')";
			else if(filter.getDateType().equals("closedDate"))
				closedDate = "(DATE(OP_CloseDate) BETWEEN '"+filter.getStartDate()+"' AND '"+filter.getEndDate()+"')";
			
			if(filter.getStage() != null && !filter.getStage().equals(""))
				stage = " AND OP_StageID = "+filter.getStage();
			
			if(filter.getType() != null && !filter.getType().equals(""))
				type = " AND OP_TypeID = "+filter.getType();
			
			if(filter.getSource() != null && !filter.getSource().equals(""))
				source = " AND OP_LeadSourceID = "+filter.getSource();
			
			if(filter.getCampaign() != null && !filter.getCampaign().equals(""))
				campaign = " AND OP_CampID = '"+filter.getCampaign()+"'";
			
			if(filter.getCustomer() != null && !filter.getCustomer().equals(""))
				customer = " AND OP_CustID = '"+filter.getCustomer()+"'";
			
			if(filter.getAssignTo() != null && !filter.getAssignTo().equals(""))
				assignTo = " AND OP_ATo = '"+filter.getAssignTo()+"'";
			
			String sql = "SELECT "
							+ "OP_ID opId,"
							+ "OP_Name opName,"
							+ "OP_StageID stageId,"
							+ "(SELECT OS_Name FROM crm_opportunity_stage WHERE OS_ID = OP_StageID) stageName,"
							+ "OP_TypeID typeId,"
							+ "(SELECT OT_Name FROM crm_opportunity_type WHERE OT_ID = OP_TypeID) typeName,"
							+ "CONCAT('$',OP_Amt) opAmount,"
							+ "CONCAT(OP_Probability,'%') opProbability,"
							+ "DATE_FORMAT(OP_CDate,'%d/%m/%Y') opCreatedDate,"
							+ "DATE_FORMAT(OP_CloseDate,'%d/%m/%Y') opClosedDate,"
							+ "OP_CustID custId,"
							+ "(SELECT CustName FROM tblcustomer WHERE CustID = OP_CustID) custName,"
							+ "OP_CampID campaignId,"
							+ "(SELECT CA_Name FROM crm_camp WHERE CA_ID = OP_CampID) campaignName,"
							+ "OP_LeadSourceID sourceId,"
							+ "(SELECT LS_Name FROM crm_lead_source WHERE LS_ID = OP_LeadSourceID) sourceName "
						+ "FROM "
							+ "crm_opportunity "
						+ "WHERE "
							+createdDate+""+closedDate+""+stage+""+type+""+source+""+campaign+""+customer+""+assignTo+";";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return new ArrayList<Map<String,Object>>();
	}

}
