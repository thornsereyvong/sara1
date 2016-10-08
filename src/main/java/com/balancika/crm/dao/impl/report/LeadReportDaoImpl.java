package com.balancika.crm.dao.impl.report;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.report.LeadReportDao;
import com.balancika.crm.model.CrmLead;

@Repository
public class LeadReportDaoImpl implements LeadReportDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CrmLead> reportLeadsConvertedAllTime() {
		//Session session = sessionFactory.getCurrentSession();
		//SQLQuery query = session.createSQLQuery("CALL ");
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> reportLeadConvertedByFQ(String startDate, String endDate) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL reportLeadsConvertedByFQ(:startDate, :endDate)");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> reportLeadCreatedCurrentFQ(String startDate, String endDate) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL reportLeadsCreatedCurrentByFQ(:startDate, :endDate)");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();
	}

	@Override
	public List<CrmLead> reportMarketingOfLead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportLeadByMonth(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object reportMarketingLeadTrendsByStatus() {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("SELECT reportMarketingExecLeadTrendsByStatus()");
		return query.uniqueResult();
	}

	@Override
	public List<CrmLead> reportMarketingLeadByCampaigns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingLeadByIndustry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingLeadBySource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmLead> reportMarketingLeadByConverted() {
		// TODO Auto-generated method stub
		return null;
	}

}
