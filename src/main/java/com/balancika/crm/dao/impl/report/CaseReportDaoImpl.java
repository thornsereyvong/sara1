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
import com.balancika.crm.dao.AmeItemDao;
import com.balancika.crm.dao.CrmCaseOriginDao;
import com.balancika.crm.dao.CrmCasePriorityDao;
import com.balancika.crm.dao.CrmCaseStatusDao;
import com.balancika.crm.dao.CrmCaseTypeDao;
import com.balancika.crm.dao.CrmContactDao;
import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.dao.report.CaseReportDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.report.CaseReport;

@Repository
public class CaseReportDaoImpl implements CaseReportDao{

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Autowired
	private CrmCaseStatusDao statusDao;
	
	@Autowired
	private CrmCaseTypeDao typeDao;
	
	@Autowired
	private CrmCaseOriginDao originDao;
	
	@Autowired
	private CrmCustomerDao customerDao;
	
	@Autowired
	private CrmContactDao contactDao;
	
	@Autowired
	private AmeItemDao itemDao;
	
	@Autowired
	private CrmUserDao userDao;
	
	@Autowired
	private CrmCasePriorityDao priorityDao;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Map<String, Object> caseReportStartup(MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", statusDao.listCaseStatus(dataSource));
		map.put("TYPES", typeDao.listCaseTypes(dataSource));
		map.put("ORIGINS", originDao.listCaseOrigins(dataSource));
		map.put("PRIORITIES", priorityDao.listCasePriorities(dataSource));
		map.put("CUSTOMERS", customerDao.listCustomerIdAndName(dataSource));
		map.put("PRODUCTS", itemDao.listItems(dataSource));
		map.put("CONTACTS", contactDao.listSomeFieldsOfContact(dataSource));
		map.put("ASSIGN_TO", userDao.listSubordinateUserByUsername(dataSource.getUserid(), dataSource));
		map.put("STATUP_DATE", startupDate(dataSource));
		return map;
	}
	
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> startupDate(MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String  sql = "SELECT MIN(DATE(CS_CDate)) startDate, MAX(DATE(CS_CDate)) endDate FROM crm_case; ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return ( Map<String, Object>)query.uniqueResult();
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
	public List<Map<String, Object>> caseReport(CaseReport report) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(report.getDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String status = "";
			String type = "";
			String origin = "";
			String priority = "";
			String product = "";
			String customer = "";
			String contact = "";
			String assignTo = "";
			if(report.getStatus() != null && !report.getStatus().equals("")) 
				status = " AND CS_StatusID = "+report.getStatus();
			
			if(report.getType() != null && !report.getType().equals(""))
				type = " AND CS_TypeID = "+report.getType();
			
			if(report.getOrigin() != null && !report.getOrigin().equals(""))
				origin = " AND crm_case.CS_OriginID = "+report.getOrigin();
			
			if(report.getPriority() != null && !report.getPriority().equals(""))
				priority = " AND CS_Priority = "+report.getPriority();
			
			if(report.getProduct() != null && !report.getProduct().equals(""))
				product = " AND CS_ItemID = '"+report.getProduct()+"'";
			
			if(report.getCustomer() != null && !report.getCustomer().equals(""))
				customer = " AND CS_CustID = '"+report.getCustomer()+"'";
			
			if(report.getContact() != null && !report.getContact().equals(""))
				contact = " AND CS_ContactID = '"+report.getContact()+"'";
			
			if(report.getAssignTo() != null && !report.getAssignTo().equals(""))
				assignTo = " AND CS_ATo = '"+report.getAssignTo()+"'";
			String sql = "SELECT "
							+ "CS_ID caseId,"
							+ "CS_Subject caseSubject,"
							+ "CS_StatusID statusId,"
							+ "(SELECT CSS_Name FROM crm_case_status WHERE CSS_ID = CS_StatusID) statusName,"
							+ "CS_TypeID typeId,"
							+ "(SELECT CST_Name FROM crm_case_type WHERE CST_ID = CS_TypeID) typeName,"
							+ "CS_Priority priorityId,"
							+ "(SELECT CSP_Name FROM crm_case_priority WHERE CSP_ID = CS_Priority) priorityName,"
							+ "CS_OriginID originId,"
							+ "(SELECT crm_case_origin.CS_Title FROM crm_case_origin WHERE crm_case_origin.CS_OriginID = crm_case.CS_OriginID) originName,"
							+ "DATE_FORMAT(CS_CDate,'%d/%m/%Y') createdDate,"
							+ "(SELECT UName FROM tbluser WHERE UID = CS_ResolvedBy) resolvedBy,"
							+ "DATE_FORMAT(CS_ResolvedDate,'%d/%m/%Y') resolvedDate,"
							+ "CS_FollowupDate followupDate,"
							+ "CS_CustID custId,"
							+ "(SELECT CustName FROM tblcustomer WHERE CustID = CS_CustID) custName,"
							+ "CS_ItemID itemId,"
							+ "(SELECT ItemName FROM tblitem WHERE itemID = CS_ItemID) itemName,"
							+ "CS_ContactID contactId,"
							+ "(SELECT CONCAT(CO_Salutation,' ',CO_FirstName,' ',CO_LastName) FROM crm_contact WHERE CO_ID = CS_ContactID) contactName,"
							+ "CS_ArticleID articleId,"
							+ "(SELECT A_Title FROM crm_case_article WHERE A_ID = CS_ArticleID) articleTitle "
							+ "FROM crm_case "
							+ "WHERE (DATE(CS_CDate) BETWEEN '"+report.getStartDate()+"' AND '"+report.getEndDate()+"')"+status+""+type+""+priority+""+origin+""+product+""+customer+""+contact+""+assignTo+"; ";
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
