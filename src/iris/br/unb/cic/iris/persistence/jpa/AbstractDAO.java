package br.unb.cic.iris.persistence.jpa;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.unb.cic.iris.MainApp;

public abstract class AbstractDAO<T, I extends Serializable> {
	private EntityManager entityManager;
	private Class<T> persistedClass;

	protected AbstractDAO(Class<T> persistedClass) {
		Thread.currentThread().setContextClassLoader(new ClassLoader() {
			public Enumeration<URL> getResources(String name) throws IOException {
				if (name.equals("META-INF/persistence.xml")) {
					return Collections
							.enumeration(Arrays.asList(MainApp.class.getResource("/META-INF/persistence.xml")));
				}
				return super.getResources(name);
			}
		});
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("IrisPU");
		entityManager = entityManagerFactory.createEntityManager();
		this.persistedClass = persistedClass;
	}

	public T persist(T entity) {
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		entityManager.persist(entity);
		entityManager.flush();
		t.commit();
		return entity;
	}

	public T merge(T entity) {
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		entityManager.merge(entity);
		entityManager.flush();
		t.commit();
		return entity;
	}

	public void removeById(I id) {
		T entity = findById(id);
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		T mergedEntity = entityManager.merge(entity);
		entityManager.remove(mergedEntity);
		entityManager.flush();
		tx.commit();
	}

	public List<T> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistedClass);
		query.from(persistedClass);
		return entityManager.createQuery(query).getResultList();
	}

	public T findById(I id) {
		return entityManager.find(persistedClass, id);
	}
}
