package br.unb.cic.iris.persistence.relational;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import br.unb.cic.iris.model.AddressBookEntry;
import br.unb.cic.iris.model.EmailMessage;
import br.unb.cic.iris.model.IrisFolder;
import br.unb.cic.iris.model.Tag;

/***
 * added by dPersistenceRelational* modified by dAddressBookRelational* modified
 * by dTagRelational
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		classList = new ArrayList<Class>();
		configureClasses();
		try {
			Configuration configuration = new Configuration();
			for (Class clazz : classList) {
				configuration.addAnnotatedClass(clazz);
			}
			configuration.configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
			Properties properties = configuration.getProperties();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable t) {
			throw new ExceptionInInitializerError(t);
		}
	}

	private static List<Class> classList;

	/***
	 * modified by dAddressBookRelational* modified by dTagRelational
	 */
	public static void configureClasses() {
		classList.add(Tag.class);
		configureClasses_original2();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	/***
	 * modified by dAddressBookRelational
	 */
	public static void configureClasses_original0() {		
		classList.add(EmailMessage.class);
		classList.add(IrisFolder.class);
	}

	/***
	 * modified by dAddressBookRelational* modified by dTagRelational
	 */
	public static void configureClasses_original2() {
		classList.add(AddressBookEntry.class);
		configureClasses_original0();
	}
}