package com.balancika.crm.utilities;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class CrmModule {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<?> listModuleDetailsByModuleName(String module){
		Session session = sessionFactory.getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listModuleDetailsByModuleName(:module)");
			query.setParameter("module", module);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> listSystemModules(){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listSystemModules()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}
