package br.unb.cic.iris.persistence.relational;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.unb.cic.iris.core.exception.DBException;
import br.unb.cic.iris.model.EmailMessage;
import br.unb.cic.iris.model.Tag;
import br.unb.cic.iris.persistence.ITagDAO;

/***
 * added by dTagRelational
 */
public class TagDAO extends AbstractDAO<Tag> implements ITagDAO {
	private static final TagDAO instance = new TagDAO();

	private TagDAO() {
	}

	public static TagDAO instance() {
		return instance;
	}

	public Tag findByName(String tagName) throws DBException {
		try {
			startSession(false);
			Criteria criteria = session.createCriteria(Tag.class);
			criteria.add(Restrictions.eq("name", tagName));
			return (Tag) criteria.uniqueResult();
		} catch (Exception e) {
			handleException(new DBException("Tag not found: " + tagName, e));
		} finally {
			closeSession();
		}
		return null;
	}

	public void save(Tag tagToSave, String messageId) throws DBException {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Tag.class);
			criteria.add(Restrictions.eq("name", tagToSave.getName()));
			Tag tag = (Tag) criteria.uniqueResult();
			if (tag == null) {
				session.saveOrUpdate(tagToSave);
				criteria = session.createCriteria(Tag.class);
				criteria.add(Restrictions.eq("name", tagToSave.getName()));
				tag = (Tag) criteria.uniqueResult();
			}
			EmailMessage msg = (EmailMessage) session.load(EmailMessage.class, messageId);
			if (tag.getMessages() == null) {
				tag.setMessages(new java.util.HashSet<EmailMessage>());
			}
			tag.getMessages().add(msg);
			session.update(tag);
			msg = (EmailMessage) session.load(EmailMessage.class, messageId);
			if (msg.getTags() == null) {
				msg.setTags(new java.util.HashSet<Tag>());
			}
			msg.getTags().add(tag);
			session.update(msg);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			handleException(new DBException("Couldn't save tag: " + tagToSave.getName(), e));
		} finally {
			try {
				if (session != null && session.isOpen()) {
					session.close();
				}
			} finally {
				session = null;
			}
		}
	}
}