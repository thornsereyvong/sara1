package com.balancika.crm.dao.impl.report;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.report.LeadReportDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;

@Repository
public class LeadReportDaoImpl implements LeadReportDao{
	
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

}
