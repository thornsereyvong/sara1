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
import com.balancika.crm.dao.CrmIndustryDao;
import com.balancika.crm.dao.CrmLeadSourceDao;
import com.balancika.crm.dao.CrmLeadStatusDao;
import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.dao.report.LeadReportDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.LeadReport;

@Repository
public class LeadReportDaoImpl implements LeadReportDao{
	
	@Autowired
	private CrmLeadStatusDao statusDao;
	
	@Autowired
	private CrmLeadSourceDao sourceDao;
	
	@Autowired
	private CrmUserDao userDao;
	
	@Autowired
	private CrmIndustryDao industryDao;
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<CrmLead> reportLeadsConvertedAllTime(MeDataSource dataSource) {
		//Session session = sessionFactory.getCurrentSession();
		//SQLQuery query = session.createSQLQuery("CALL ");
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> reportLeadConvertedByFQ(String startDate, String endDate, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL reportLeadsConvertedByFQ(:startDate, :endDate)");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> reportLeadCreatedCurrentFQ(String startDate, String endDate, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL reportLeadsCreatedCurrentByFQ(:startDate, :endDate)");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingOfLead(MeDataSource dataSource) {
		return null;
	}

	@Override
	public List<CrmLead> reportLeadByMonth(String date, MeDataSource dataSource) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> reportMarketingLeadTrendsByStatus(MeDataSource dataSource) {
		
 		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL reportMarketingExecLeadTrendsByStatus()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> reportMarketingLeadByCampaigns(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL reportMarketingExecLeadsByCampaigns()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}

	@Override
	public List<CrmLead> reportMarketingLeadByIndustry(MeDataSource dataSource) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> reportMarketingLeadBySource(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL reportMarketingLeadsExecBySource()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingLeadByConverted(MeDataSource dataSource) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> reportLead(LeadReport leadReport) {
		
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(leadReport.getDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String createdDate = "";
			String convertedDate = "";
			String status = "";
			String source = "";
			String assignTo = "";
			String industry = "";
			
			if(leadReport.getDateType().equals("createdDate")){
				createdDate = "DATE(LA_CDate) BETWEEN '"+leadReport.getStartDate()+"' AND '"+leadReport.getEndDate()+"'";
			}
			
			if(leadReport.getDateType().equalsIgnoreCase("convertedDate")){
				convertedDate = "DATE(LA_ConvertedDate) BETWEEN '"+leadReport.getStartDate()+"' AND '"+leadReport.getEndDate()+"'";
			}
			if(leadReport.getStatus() != null && !leadReport.getStatus().equals("")){
				status = " AND LA_StatusID = "+leadReport.getStatus();
			}
			
			if(leadReport.getSource() !=null && !leadReport.getSource().equals("")){
				source = " AND LA_SourceID = "+leadReport.getSource();
			}
			
			if(leadReport.getAssignTo()!=null && !leadReport.getAssignTo().equals("")){
				assignTo = " AND LA_ATo = '"+leadReport.getAssignTo()+"'";
			}
			
			if(leadReport.getIndustry() != null && !leadReport.getIndustry().equals(""))
				industry = " AND LA_IndustID = "+leadReport.getIndustry();
			
			SQLQuery query = session.createSQLQuery(""
					+ "SELECT "
						+ "LA_ID leadId, "
						+ "CONCAT(LA_Salutation,' ',LA_FirstName,' ',LA_LastName) leadName, "
						+ "LA_AccountName company,"
						+ "LA_SourceID sourceId,"
						+ "(SELECT LS_Name FROM crm_lead_source WHERE LS_ID = LA_SourceID) sourceName,"
						+ "LA_StatusID statusId,"
						+ "(SELECT LST_Name FROM crm_lead_status WHERE LST_ID = LA_StatusID) statusName,"
						+ "DATE_FORMAT(LA_CDate,'%d/%m/%Y') createdDate,"
						+ "DATE_FORMAT(LA_ConvertedDate,'%d/%m/%Y') convertedDate,"
						+ "LA_OPID opId,"
						+ "op.OP_Amt opAmount,"
						+ "op.OP_Name opName,"
						+ "(SELECT UName FROM tbluser WHERE UID = op.OP_CBy) opOwner,"
						+ "LA_IndustID industId,"
						+ "(SELECT I_Name FROM crm_industry WHERE I_ID = LA_IndustID) industName"
					+ " FROM "
						+ "crm_lead "
					+ "LEFT OUTER JOIN "
						+ "crm_opportunity op "
							+ "ON op.OP_ID = LA_OPID "
					+ "WHERE "+createdDate+""+convertedDate+""+status+""+source+""+assignTo+""+industry+";");
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

	@Override
	public Map<String, Object> startupReportLead(MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", statusDao.getAllLeadStatus(dataSource));
		map.put("SOURCE", sourceDao.getAllLeadSource(dataSource));
		map.put("ASSIGN_TO", userDao.listSubordinateUserByUsername(dataSource.getUserid(), dataSource));
		map.put("STARTUP_DATE", startupDate("createdDate", dataSource));
		map.put("INDUSTRIES", industryDao.listIndustries(dataSource));
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> startupDate(String dateType, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String sql = "";
			if(dateType.equals("createdDate")){
				sql = "SELECT MIN(DATE_FORMAT(LA_CDate,'%Y-%m-%d')) startDate, MAX(DATE_FORMAT(LA_CDate,'%Y-%m-%d')) endDate FROM crm_lead; ";
			}else if(dateType.equals("convertedDate")){
				sql = "SELECT MIN(DATE_FORMAT(LA_ConvertedDate,'%Y-%m-%d')) startDate, MAX(DATE_FORMAT(LA_ConvertedDate,'%Y-%m-%d')) endDate FROM crm_lead; ";
			}
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return (Map<String, Object>)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}
	
}
