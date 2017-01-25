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
import com.balancika.crm.dao.CrmCampaignStatusDao;
import com.balancika.crm.dao.CrmCampaignTypeDao;
import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.dao.report.CampaignReportDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.CampaingReport;

@Repository
public class CampaignReportDaoImpl implements CampaignReportDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	private CrmCampaignDao campaignDao;

	@Autowired
	private CrmCampaignStatusDao statusDao;
	
	@Autowired
	private CrmCampaignTypeDao typeDao;
	
	@Autowired
	private CrmUserDao userDao;
	
	@Override
	public Map<String, Object> campaignReportStartup(String userId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", statusDao.listAllCampaignStatus(dataSource));
		map.put("TYPES", typeDao.listAllCampaignType(dataSource));
		map.put("CAMPAIGNS", campaignDao.listCampaignParents(dataSource));
		map.put("ASSIGN_TO", userDao.listSubordinateUserByUsername(userId, dataSource));
		map.put("DEFAULT_DATE", getMinStartDateAndMaxEndDate(dataSource));
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String> getMinStartDateAndMaxEndDate(MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = sessionFactory.openSession();
		try {
			SQLQuery query = session.createSQLQuery("SELECT DATE_FORMAT(MIN(CA_SDate),'%d/%m/%Y') AS startDate,DATE_FORMAT(MAX(CA_EDate),'%d/%m/%Y') AS endDate FROM  crm_camp; ");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return (Map<String, String>)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> reportTopCampaign(CampaingReport campaingReport) {
		
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(campaingReport.getDataSource()));
		Session session = sessionFactory.openSession();
		try {
			String sDate = "";
			String eDate = "";
			String status = "";
			String type = "";
			String campaign = "";
			String user = "";
			
			if(!campaingReport.getStartDate().equals("")){
				sDate = "DATE_FORMAT(c.CA_SDate,'%d/%m/%Y') ='"+campaingReport.getStartDate()+"'";
			}
			
			if(!campaingReport.getEndDate().equals("") && !campaingReport.getStartDate().equals("")){
				sDate = "((DATE_FORMAT(c.CA_SDate,'%d/%m/%Y') BETWEEN'"+campaingReport.getStartDate()+"' AND '"+campaingReport.getEndDate()+"')";
				eDate = "OR (DATE_FORMAT(c.CA_EDate,'%d/%m/%Y') BETWEEN '"+campaingReport.getStartDate()+"' AND '"+campaingReport.getEndDate()+"'))";
			}else if(!campaingReport.getEndDate().equals("")){
				eDate = "DATE_FORMAT(c.CA_EDate,'%d/%m/%Y') ='"+campaingReport.getEndDate()+"'";
			}
			
			if(campaingReport.getStatusId() != 0){
				 status = "AND c.CA_StatusID ="+campaingReport.getStatusId();
			}
			
			if(campaingReport.getTypeId() != 0){
				type = "AND c.CA_TypeID ="+campaingReport.getTypeId();
			}
			
			if(  campaingReport.getCampParentId() != null && !campaingReport.getCampParentId().equals("")){
				campaign = "AND c.CA_ParentID ='"+campaingReport.getCampParentId()+"'";
			}
			
			if(campaingReport.getUserId() != null && !campaingReport.getUserId().equals("")){
				user = "AND c.CA_ATo ='"+campaingReport.getUserId()+"'";
			}
			
			SQLQuery query = session.createSQLQuery(""
					+ "SELECT * "
						+ "FROM (	"
							+ "SELECT "
								+ "c.CA_ID 'campId', "
								+ "c.CA_Name 'campName', "
								+ "c.CA_TypeID 'typeId',"
								+ "(SELECT t.CAT_Name FROM crm_camp_type t WHERE t.CAT_ID=c.CA_TypeID) as 'typeName',"
								+ "c.CA_StatusID statusId, "
								+ "(SELECT s.CAS_Name FROM crm_camp_status s WHERE s.CAS_ID = c.CA_StatusID) as 'statusName',"
								+ "DATE_FORMAT(c.CA_SDate,'%d/%m/%Y') 'startDate', "
								+ "DATE_FORMAT(c.CA_EDate,'%d/%m/%Y') 'endDate',"
								+ "c.CA_NumSend 'numSent', "
								+ "c.CA_ATo 'assignTo', "
								+ "c.CA_Budget 'budget', "
								+ "c.CA_ActualCost 'actCost', "
								+ "c.CA_ExpectedCost 'expCost',"
								+ "c.CA_ExpectedRevenue 'expRevenue', "
								+ "c.CA_ExpectedResponse 'expResponse',"
								+ "COALESCE((SELECT COUNT(*) FROM crm_opportunity opp WHERE opp.OP_CampID=c.CA_ID GROUP BY opp.OP_CampID),0) 'totalOpp',"
								+ "COALESCE((SELECT COUNT(*) FROM crm_opportunity opp WHERE opp.OP_CampID=c.CA_ID AND opp.OP_StageID=6 GROUP BY opp.OP_CampID),0) 'totalOppWon',"
								+ "COALESCE((SELECT sum(opp.OP_Amt) FROM crm_opportunity opp WHERE opp.OP_CampID=c.CA_ID AND opp.OP_StageID=6 GROUP BY opp.OP_CampID),0) 'totalAmtWon'"
							+ "FROM crm_camp c WHERE "+sDate+" "+eDate+" "+status+" "+type+" "+campaign+" "+user+")AS tbl "
						+ "ORDER BY totalAmtWon DESC ");
			
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return (List<Map<String, Object>>)query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Map<String,Object>>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> reportLeadByCampaing(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = sessionFactory.openSession();
		try {
			SQLQuery query = session.createSQLQuery(""
					+ "SELECT "
						+ "ca.CA_ID AS campId,"
						+ "ca.CA_Name AS campName,"
						+ "le.LA_ID AS leadId,"
						+ "CONCAT(le.LA_Salutation,' ',le.LA_FirstName,' ',le.LA_LastName) leadName,"
						+ "le.LA_AccountName AS leadCompany,"
						+ "le.LA_Email AS leadEmail,"
						+ "le.LA_StatusID AS leadStatusId,"
						+ "COALESCE((SELECT LST_Name FROM crm_lead_status WHERE LST_ID = le.LA_StatusID), '') AS leadStatus,"
						+ "DATE_FORMAT(le.LA_CDate,'%d/%m/%Y') AS leadCreatedDate,"
						+ "le.LA_CBy AS leadCreateBy,"
						+ "(SELECT em.EmpName FROM tblemployee em WHERE em.EmpID = (SELECT ue.EmpID FROM tbluseremployee ue WHERE ue.UID = le.LA_CBy)) AS leadOwner,"
						+ "(SELECT COUNT(lea.LA_ID) FROM crm_lead lea WHERE lea.LA_CA_ID = le.LA_CA_ID) AS numOfLeadPerCamp"
						+ " FROM"
							+ "	crm_camp ca "
						+ "INNER JOIN crm_lead le ON ca.CA_ID = le.LA_CA_ID;");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return (List<Map<String, Object>>)query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Map<String,Object>>();
	}
}
