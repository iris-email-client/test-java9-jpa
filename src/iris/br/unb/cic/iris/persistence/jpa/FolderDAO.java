package br.unb.cic.iris.persistence.jpa;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.IrisFolder;
import br.unb.cic.iris.persistence.IFolderDAO;

public class FolderDAO extends AbstractDAO<IrisFolder, String> implements IFolderDAO {
	public FolderDAO() {
		super(IrisFolder.class);
	}

	@Override
	public IrisFolder findByName(String folderName) throws EmailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IrisFolder createFolder(String folderName) throws EmailException {
		return super.persist(new IrisFolder(folderName));
	}
}
