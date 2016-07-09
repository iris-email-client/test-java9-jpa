package br.unb.cic.iris;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.AddressBookEntry;
import br.unb.cic.iris.model.EmailMessage;
import br.unb.cic.iris.model.IrisFolder;
import br.unb.cic.iris.model.Tag;
import br.unb.cic.iris.persistence.IAddressBookDAO;
import br.unb.cic.iris.persistence.IFolderDAO;
import br.unb.cic.iris.persistence.ITagDAO;
import br.unb.cic.iris.persistence.jpa.AddressBookDAO;
import br.unb.cic.iris.persistence.jpa.EmailMessageDAO;
import br.unb.cic.iris.persistence.jpa.FolderDAO;
import br.unb.cic.iris.persistence.jpa.TagDAO;

public class MainApp {

	static EmailMessage createEmailMessage(IrisFolder folder, String subject) throws EmailException{
		EmailMessage message = new EmailMessage();
		message.setFrom("from@gmail.com");
		message.setTo("to@gmail.com");
		message.setDate(new Date());
		message.setSubject("SUBJECT - "+message.getDate());
		message.setMessage("CONTENT - "+message.getDate());
		
		System.out.println("Saving Message ...");
		message.setFolder(folder);
		message = new EmailMessageDAO().saveMessage(message);
		System.out.println("Message saved: "+message.getId());
		
		return message;
	}
	static void testJPA() throws EmailException {
		IFolderDAO folderDao = new FolderDAO();		
		IAddressBookDAO adbDao = new AddressBookDAO();
		ITagDAO tagDao = new TagDAO();
		
		System.out.println("Saving folder ...");		
		IrisFolder folder = folderDao.createFolder("INBOX");
		System.out.println("Folder saved: "+folder.getId());
				
		Set<EmailMessage> messages = new HashSet<>();
		messages.add(createEmailMessage(folder,"TEST"));
		messages.add(createEmailMessage(folder,"TEST 123"));
		messages.add(createEmailMessage(folder,"TEST 12345"));

		System.out.println("Saving tag ...");
		Tag tag = new Tag();
		tag.setName("tag");
		tag.setMessages(messages);
		tag = tagDao.saveOrUpdate(tag);
		System.out.println("Tag saved: "+tag.getId());
		
		System.out.println("Saving adb entry ...");
		AddressBookEntry entry = new AddressBookEntry("test", "test@gmail.com");
		entry = adbDao.save(entry);
		System.out.println("ENTRY saved: "+entry.getId());	
		
		
		System.out.println("Searching tag ...");
		tag = tagDao.findByName("tag");
		System.out.println("TAG: "+tag.getId()+" === "+tag.getName());
		System.out.println("MESSAGES count: "+tag.getMessages().size());
		tag.getMessages().forEach(m -> System.out.println("\t MESSAGE: "+m.getId()+" --- "+m.getSubject()));
	}

	public static void main(String[] args) {		
		try {
			testJPA();
		} catch (EmailException e) {			
			e.printStackTrace();
		}
		System.exit(0);
	}
}
