package com.balancika.crm.utilities;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.model.MeDataSource;


@Service
@Transactional
public class CrmModule {

	public List<?> listModuleDetailsByModuleName(String module, MeDataSource dataSource){
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listModuleDetailsByModuleName(:module)");
			query.setParameter("module", module);
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
	public List<Object> listSystemModules(MeDataSource dataSource){
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listSystemModules()");
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
}
