package br.unb.cic.iris.persistence.jpa;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.AddressBookEntry;
import br.unb.cic.iris.persistence.IAddressBookDAO;

public class AddressBookDAO extends AbstractDAO<AddressBookEntry, String> implements IAddressBookDAO {
	public AddressBookDAO() {
		super(AddressBookEntry.class);
	}
	// TODO implement ...

	@Override
	public AddressBookEntry save(AddressBookEntry entry) throws EmailException {
		return super.persist(entry);
	}

	@Override
	public AddressBookEntry find(String nick) throws EmailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String nick) throws EmailException {
		// TODO Auto-generated method stub
		
	}
}
