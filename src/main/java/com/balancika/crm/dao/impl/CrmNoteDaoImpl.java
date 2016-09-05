package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmNoteDao;
import com.balancika.crm.model.CrmNote;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmNoteDaoImpl extends CrmIdGenerator implements CrmNoteDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertNote(CrmNote note) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			note.setNoteId(IdAutoGenerator("AC_NO"));
			session.save(note);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateNote(CrmNote note) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(note);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteNote(String noteId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmNote note = new CrmNote();
			note.setNoteId(noteId);
			session.delete(note);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmNote> listNotes() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmNote.class);
		criteria.addOrder(Order.asc("noteId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmNote findNoteById(String noteId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmNote.class);
		criteria.add(Restrictions.eq("noteId", noteId));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (CrmNote) criteria.uniqueResult();
	}
}
