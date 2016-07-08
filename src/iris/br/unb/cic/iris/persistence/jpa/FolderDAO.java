package br.unb.cic.iris.persistence.jpa;

import br.unb.cic.iris.model.IrisFolder;

public class FolderDAO extends AbstractDAO<IrisFolder, String> {
	public FolderDAO() {
		super(IrisFolder.class);
	}
}
