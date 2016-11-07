package com.balancika.crm.dao.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmAccountTypeDao;
import com.balancika.crm.model.CrmAccountType;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmAccountTypeDaoImpl implements CrmAccountTypeDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertAccountType(CrmAccountType accountType) {
		Session session = new HibernateSessionFactory().getSessionFactory(accountType.getMeDataSource()).openSession();//transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(accountType);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} catch (ConstraintViolationException e) {
			System.out.println("Account Type Name is Empty!");
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateAccountType(CrmAccountType accountType) {
		Session session = new HibernateSessionFactory().getSessionFactory(accountType.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(accountType);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteAccountType(CrmAccountType accountType) {
		Session session = new HibernateSessionFactory().getSessionFactory(accountType.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(accountType);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			System.out.println("Your AccountTypeId Not Exist!");
		} finally {
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmAccountType> listAccountTypes(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmAccountType.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public CrmAccountType findAccountTypeById(CrmAccountType accountType) {
		Session session = new HibernateSessionFactory().getSessionFactory(accountType.getMeDataSource()).openSession();
		try {
			return (CrmAccountType) session.get(CrmAccountType.class, accountType.getAccountID());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
