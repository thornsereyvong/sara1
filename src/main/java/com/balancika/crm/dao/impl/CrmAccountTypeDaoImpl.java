package com.balancika.crm.dao.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmAccountTypeDao;
import com.balancika.crm.model.CrmAccountType;

@Repository
public class CrmAccountTypeDaoImpl implements CrmAccountTypeDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertAccountType(CrmAccountType accountType) {
		Session session = transactionManager.getSessionFactory().openSession();
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
		Session session = transactionManager.getSessionFactory().openSession();
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
	public boolean deleteAccountType(int accountTypeID) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmAccountType accountType = new CrmAccountType();
			accountType.setAccountID(accountTypeID);
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
	public List<CrmAccountType> listAccountTypes() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmAccountType.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmAccountType findAccountTypeById(int accountTypeId) {
		return (CrmAccountType) transactionManager.getSessionFactory().getCurrentSession().get(CrmAccountType.class, accountTypeId);
	}

}
