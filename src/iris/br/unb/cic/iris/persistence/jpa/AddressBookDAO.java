package br.unb.cic.iris.persistence.jpa;

import br.unb.cic.iris.model.AddressBookEntry;

public class AddressBookDAO extends AbstractDAO<AddressBookEntry, String> {
	public AddressBookDAO() {
		super(AddressBookEntry.class);
	}
	// TODO implement ...
}
