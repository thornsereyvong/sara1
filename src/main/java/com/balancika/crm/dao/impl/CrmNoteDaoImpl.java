package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmNoteDao;
import com.balancika.crm.model.CrmNote;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DateTimeOperation;

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
			note.setNoteCreateDate(LocalDateTime.now());
			session.save(note);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
		CrmNote note = (CrmNote)criteria.uniqueResult();
		note.setCreateDate(new DateTimeOperation().reverseLocalDateTimeToFormate(note.getNoteCreateDate(), "d-MM-YYYY"));
		note.setCreateTime(new DateTimeOperation().reverseLocalDateTimeToFormate(note.getNoteCreateDate(), "h:mm a"));
		note.setCreateDateTime(new DateTimeOperation().reverseLocalDateTimeToString(note.getNoteCreateDate()));
		return note;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmNote> listNoteRelatedToLead(String leadId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listNotesRelatedToLead(:leadId)");
				query.setParameter("leadId", leadId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmNote> listNotesRelatedToOpportunity(String opId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listNotesRelatedToOpportunity(:opId)");
				query.setParameter("opId", opId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmNote> listNoteRelatedToEachModule(String moduleId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmNote.class);
			criteria.add(Restrictions.eq("noteRelatedToModuleId", moduleId));
			List<CrmNote> notes = criteria.list();
			for(CrmNote note : notes){
				note.setCreateDate(new DateTimeOperation().reverseLocalDateTimeToFormate(note.getNoteCreateDate(), "d-MM-YYYY"));
				note.setCreateTime(new DateTimeOperation().reverseLocalDateTimeToFormate(note.getNoteCreateDate(), "h:mm a"));
			}
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
