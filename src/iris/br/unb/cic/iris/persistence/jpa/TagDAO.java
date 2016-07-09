package br.unb.cic.iris.persistence.jpa;

import javax.persistence.Query;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.model.Tag;
import br.unb.cic.iris.persistence.ITagDAO;

public class TagDAO extends AbstractDAO<Tag, String> implements ITagDAO {
	public TagDAO() {
		super(Tag.class);
	}
	// TODO implement ...

	@Override
	public Tag findByName(String name) throws EmailException {
		Query query = this.entityManager.createQuery("select t FROM Tag t where t.name= :name");
        query.setParameter("name", name);
        return (Tag) query.getSingleResult();
	}

	@Override
	public Tag saveOrUpdate(Tag tag) throws EmailException {
		return super.persist(tag);
	}

	@Override
	public void save(Tag tag, String messageId) throws EmailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tag t) throws EmailException { 
		super.removeById(t.getId());
	}
}
