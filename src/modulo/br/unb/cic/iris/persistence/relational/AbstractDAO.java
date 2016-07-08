package br.unb.cic.iris.persistence.relational;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;

import br.unb.cic.iris.core.exception.DBException;

/***
 * added by dPersistenceRelational
 */
public abstract class AbstractDAO<T> {
	private Class<T> clazz;
	protected Session session;

	public AbstractDAO() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void save(T obj) throws DBException {
		try {
			startSession(true);
			session.save(obj);
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			closeSession();
		}
	}
	
	public void saveOrUpdate(T obj) throws DBException {
		try {
			startSession(true);
			session.saveOrUpdate(obj);
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			closeSession();
		}
	}

	public void delete(T t) throws DBException {
		try {
			startSession(true);
			session.delete(t);
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			closeSession();
		}
	}

	public T findById(String uuid) throws DBException {
		T obj = null;
		try {
			startSession(false);
			obj = (T) session.load(clazz, uuid);
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			closeSession();
		}
		return obj;
	}

	public List<T> findAll() throws DBException {
		List<T> objects = null;
		try {
			startSession(false);
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			closeSession();
		}
		return objects;
	}

	public List<T> findByExample(T filtro, MatchMode matchMode, boolean ignoreCase) throws DBException {
		try {
			startSession(false);
			org.hibernate.criterion.Example example = org.hibernate.criterion.Example.create(filtro);
			if (matchMode != null) {
				example = example.enableLike(matchMode);
			}
			if (ignoreCase) {
				example = example.ignoreCase();
			}
			return session.createCriteria(clazz).add(example).list();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			closeSession();
		}
		return new java.util.ArrayList<T>();
	}

	protected void handleException(Exception e) throws DBException {
		if (session.getTransaction().isActive()) {
			session.getTransaction().rollback();
		}
		throw new DBException(e.getMessage(), e);
	}

	protected void startSession(boolean transactional) throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		//if (transactional) {
			session.beginTransaction();
		//}
	}

	protected void closeSession() {
		try {
			if (session != null && session.isOpen()) {
				if (session.getTransaction().isActive()) {
					session.getTransaction().commit();
				}
				session.flush();
				session.close();
			}
		} finally {
			session = null;
		}
	}
}