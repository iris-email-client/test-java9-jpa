package br.unb.cic.iris.persistence;

import java.util.Date;
import java.util.List;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.EmailMessage;

/***
 * added by dBasePersistence* modified by dTagBase
 */
public interface IEmailDAO {
	public void saveMessage(EmailMessage message) throws EmailException;

	public Date lastMessageReceived() throws EmailException;

	public List<EmailMessage> listMessages(String idFolder) throws EmailException;

	/***
	 * added by dTagBase
	 */
	public List<EmailMessage> listMessagesByTag(String tag) throws EmailException;
}