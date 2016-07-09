package br.unb.cic.iris.persistence.jpa;

import java.util.Date;
import java.util.List;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.EmailMessage;
import br.unb.cic.iris.persistence.IEmailDAO;

public class EmailMessageDAO extends AbstractDAO<EmailMessage, String> implements IEmailDAO {
	public EmailMessageDAO() {
		super(EmailMessage.class);
	}
	// TODO implement ...

	@Override
	public EmailMessage saveMessage(EmailMessage message) throws EmailException {
		return super.persist(message);
	}

	@Override
	public Date lastMessageReceived() throws EmailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmailMessage> listMessages(String idFolder) throws EmailException {		
		return super.findAll();
	}

	
	
	@Override
	public List<EmailMessage> listMessagesByTag(String tag) throws EmailException {
		// TODO Auto-generated method stub
		return null;
	}
}
