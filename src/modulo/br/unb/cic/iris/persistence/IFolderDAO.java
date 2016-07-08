package br.unb.cic.iris.persistence;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.IrisFolder;

/***
 * added by dBasePersistence
 */
public interface IFolderDAO {
	public IrisFolder findByName(String folderName) throws EmailException;

	public IrisFolder findById(String id) throws EmailException;
}