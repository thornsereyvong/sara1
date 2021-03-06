package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmNoteDao;
import com.balancika.crm.model.CrmNote;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmNoteDaoImpl extends CrmIdGenerator implements CrmNoteDao {

	private SessionFactory sessionFactory;
	
	public  SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public  void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertNote(CrmNote note) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(note.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			note.setNoteId(IdAutoGenerator("AC_NO", note.getMeDataSource()));
			note.setNoteCreateDate(LocalDateTime.now());
			session.save(note);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateNote(CrmNote note) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(note.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(note);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteNote(CrmNote note) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(note.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(note);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmNote> listNotes(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmNote.class);
			criteria.addOrder(Order.desc("noteId"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
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
	public CrmNote findNoteById(String noteId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmNote.class);
			criteria.add(Restrictions.eq("noteId", noteId));
			CrmNote note = (CrmNote)criteria.uniqueResult();
			note.setCreateDate(new DateTimeOperation().reverseLocalDateTimeToFormate(note.getNoteCreateDate(), "d-MM-YYYY"));
			note.setCreateTime(new DateTimeOperation().reverseLocalDateTimeToFormate(note.getNoteCreateDate(), "h:mm a"));
			note.setCreateDateTime(new DateTimeOperation().reverseLocalDateTimeToString(note.getNoteCreateDate()));
			SQLQuery query  = session.createSQLQuery("SELECT findModuleDetailsByModuleNameAndItsId(:moduleName, :moduleId)");
			query.setParameter("moduleName", note.getNoteRelatedToModuleType());
			query.setParameter("moduleId", note.getNoteRelatedToModuleId());
			note.setNoteRelatedName((String)query.uniqueResult());
			return note;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmNote> listNoteRelatedToLead(String leadId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listNotesRelatedToLead(:leadId)");
				query.setParameter("leadId", leadId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmNote> listNotesRelatedToOpportunity(String opId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listNotesRelatedToOpportunity(:opId)");
				query.setParameter("opId", opId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmNote> listNoteRelatedToEachModule(String moduleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmNote.class);
			criteria.add(Restrictions.eq("noteRelatedToModuleId", moduleId));
			criteria.addOrder(Order.desc("noteCreateDate"));
			List<CrmNote> notes = criteria.list();
			for(CrmNote note : notes){
				note.setCreateDate(new DateTimeOperation().reverseLocalDateTimeToFormate(note.getNoteCreateDate(), "d-MM-YYYY"));
				note.setCreateTime(new DateTimeOperation().reverseLocalDateTimeToFormate(note.getNoteCreateDate(), "h:mm a"));
			}
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}
}
