package com.balancika.crm.dao.impl.report;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.report.CustomerReportDao;
import com.balancika.crm.model.report.CustomerReport;
import com.balancika.crm.model.report.CustomerReportFilter;
import com.balancika.crm.model.report.OpportunityModel;

@Repository
public class CustomerReportDaoImpl implements CustomerReportDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OpportunityModel> opportunities(CustomerReportFilter filter) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(filter.getDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CustomerReport.class,"cust");
			criteria.createAlias("cust.opportunities", "op");
			if(filter.getDateType() != null){
				if(filter.getDateType().equals("createdDate")){
					criteria.add(Restrictions.between("op.opCreatedDate", filter.getStartDate(), filter.getEndDate()));
				}
				if(filter.getDateType().equals("closedDate") && filter.getDateType() != null){
					criteria.add(Restrictions.between("op.opCloseDate", filter.getStartDate(), filter.getEndDate()));
				}
			}
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return new ArrayList<OpportunityModel>();
	}

}
