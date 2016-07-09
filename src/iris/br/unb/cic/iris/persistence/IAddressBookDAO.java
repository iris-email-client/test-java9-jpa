package br.unb.cic.iris.persistence;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.AddressBookEntry;

/***
 * added by dAddressBook
 */
public interface IAddressBookDAO {
	public AddressBookEntry save(AddressBookEntry entry) throws EmailException;

	public AddressBookEntry find(String nick) throws EmailException;

	public void delete(String nick) throws EmailException;
}