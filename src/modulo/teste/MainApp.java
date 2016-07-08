package teste;

import java.io.IOException;
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

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.IrisFolder;
import br.unb.cic.iris.persistence.relational.FolderDAO;

public class MainApp {
	private EntityManager entityManager;

	public MainApp() {
		Thread.currentThread().setContextClassLoader(new ClassLoader() {
			@Override
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
	}

	public void save(IrisFolder folder) {

	}

	static void testIRIS() throws EmailException {
		System.out.println("TESTANDO IRIS ************************************");
		IrisFolder folder = new IrisFolder("INBOX");

		FolderDAO folderDao = FolderDAO.instance();
		folderDao.save(folder);

		System.out.println("LISTANDO **************************************************************8");
		folderDao.findAll().forEach(f -> System.out.println(f.getName()));
	}

	static void testJPA() {
		Thread.currentThread().setContextClassLoader(new ClassLoader() {
			@Override
			public Enumeration<URL> getResources(String name) throws IOException {
				if (name.equals("META-INF/persistence.xml")) {
					return Collections
							.enumeration(Arrays.asList(MainApp.class.getResource("/META-INF/persistence.xml")));
				}
				return super.getResources(name);
			}
		});
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("IrisPU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println("SALVANDO ............");
		IrisFolder folder = new IrisFolder("INBOX");
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(folder);
		entityManager.flush();
		tx.commit();
		System.out.println("SALVOU ............");

		System.out.println("LISTANDO ............");
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<IrisFolder> query = builder.createQuery(IrisFolder.class);
		query.from(IrisFolder.class);
		List<IrisFolder> list = entityManager.createQuery(query).getResultList();
		System.out.println("LIST= " + list.size());
		list.forEach(f -> System.out.println(f));

	}

	public static void main(String[] args) {
		System.out.println("INICIANDO .......................");
		// HibernateUtil.getSessionFactory();

		testJPA();

		// br.unb.cic.iris.persistence.relational.HibernateUtil.getSessionFactory();
		/*
		 * try { testIRIS(); } catch (EmailException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

	}
}
