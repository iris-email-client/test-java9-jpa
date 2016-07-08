package teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static List<Class> classList;

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

	public static void configureClasses() {
		classList.add(Stock.class);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}