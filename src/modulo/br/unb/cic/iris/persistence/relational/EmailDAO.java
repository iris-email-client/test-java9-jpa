package br.unb.cic.iris.persistence.relational;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.unb.cic.iris.core.exception.DBException;
import br.unb.cic.iris.model.EmailMessage;
import br.unb.cic.iris.model.IrisFolder;
import br.unb.cic.iris.persistence.IEmailDAO;

/***
 * added by dPersistenceRelational* modified by dTagRelational
 */
public final class EmailDAO extends AbstractDAO<EmailMessage> implements IEmailDAO {
	private static final String FIND_MAX_DATE = "select max(e.date) FROM EmailMessage e";
	private static EmailDAO instance = new EmailDAO();

	private EmailDAO() {
	}

	public static EmailDAO instance() {
		return instance;
	}

	@Override
	public void saveMessage(EmailMessage message) throws DBException {
		super.saveOrUpdate(message);
	}

	@Override
	public Date lastMessageReceived() throws DBException {
		Date date = null;
		try {
			startSession(false);
			date = (Date) session.createQuery(FIND_MAX_DATE).uniqueResult();
		} finally {
			closeSession();
		}
		return date;
	}

	public EmailMessage findById(String id) throws DBException {
		EmailMessage obj = null;
		try {
			startSession(false);
			obj = populate((EmailMessage) session.load(EmailMessage.class, id));
		} catch (Exception e) {
			handleException(e);
		} finally {
			closeSession();
		}
		return obj;
	}

	public List<EmailMessage> listMessages(String idFolder) throws DBException {
		List<EmailMessage> messages = new java.util.ArrayList<EmailMessage>();
		try {
			startSession(false);
			Criteria criteria = session.createCriteria(EmailMessage.class).createCriteria("folder")
					.add(Restrictions.idEq(idFolder));
			messages = populate((List<EmailMessage>) criteria.list());
		} catch (Exception e) {
			handleException(new DBException("Error listing messages from folder: " + idFolder, e));
		} finally {
			closeSession();
		}
		return messages;
	}

	private List<EmailMessage> populate(List<EmailMessage> in) {
		List<EmailMessage> list = new java.util.ArrayList<EmailMessage>();
		for (EmailMessage msg : in) {
			list.add(populate(msg));
		}
		return list;
	}

	private EmailMessage populate(EmailMessage in) {
		if (in == null) {
			return null;
		}
		EmailMessage msg = new EmailMessage(in.getFrom(), in.getTo(), in.getCc(), in.getBcc(), in.getSubject(),
				in.getMessage());
		msg.setId(in.getId());
		msg.setFolder(new IrisFolder(in.getFolder().getId(), in.getFolder().getName()));
		msg.setDate(in.getDate());
		return msg;
	}

	/***
	 * added by dTagRelational
	 */
	@Override
	public List<EmailMessage> listMessagesByTag(String tag) throws DBException {
		List<EmailMessage> messages = new java.util.ArrayList<EmailMessage>();
		try {
			startSession(false);
			Criteria criteria = session.createCriteria(EmailMessage.class).createCriteria("tags")
					.add(Restrictions.eq("name", tag));
			messages = populate((List<EmailMessage>) criteria.list());
			System.out.println("messages: " + messages);
		} catch (Exception e) {
			handleException(new DBException("Error listing messages with tag: " + tag, e));
		} finally {
			closeSession();
		}
		return messages;
	}
}