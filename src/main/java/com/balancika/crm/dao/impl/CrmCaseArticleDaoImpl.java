package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCaseArticleDao;
import com.balancika.crm.model.CrmCaseArticle;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmCaseArticleDaoImpl extends CrmIdGenerator implements CrmCaseArticleDao{
	
	private SessionFactory sessionFactory;
		
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertCaseArticle(CrmCaseArticle article) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(article.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			article.setArticleId(IdAutoGenerator("ART", article.getMeDataSource()));
			article.setArticleCreateDate(LocalDateTime.now());
			session.save(article);
			session.getTransaction().commit();
			return true;	
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateCaseArticle(CrmCaseArticle article) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(article.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(article);
			session.getTransaction().commit();
			return true;	
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteCaseArticle(CrmCaseArticle article) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(article.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_case_article WHERE A_ID = :articleId");
			query.setParameter("articleId", article.getArticleId());
			if(query.executeUpdate() > 0){
				session.getTransaction().commit();
				return true;	
			}
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCaseArticle> listCaseArticles(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmCaseArticles()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public Object findCaseArticleById(String caseId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmCaseArticleById(:caseId)");
			query.setParameter("caseId", caseId);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public CrmCaseArticle findCaseArticleDetailsById(String caseId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCaseArticle.class);
			criteria.add(Restrictions.eq("caseId", caseId));
			return (CrmCaseArticle)criteria.uniqueResult();
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
