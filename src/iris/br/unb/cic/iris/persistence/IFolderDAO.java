package br.unb.cic.iris.persistence;

import java.util.List;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.IrisFolder;

/***
 * added by dBasePersistence
 */
public interface IFolderDAO {
	public IrisFolder createFolder(String folderName) throws EmailException;
	public IrisFolder findByName(String folderName) throws EmailException;
	public IrisFolder findById(String id) throws EmailException;
	public List<IrisFolder> findAll() throws EmailException;
}