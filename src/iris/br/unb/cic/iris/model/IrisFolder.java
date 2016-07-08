package br.unb.cic.iris.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TB_FOLDER")
public class IrisFolder {
	public static final String INBOX = "INBOX";
	public static final String OUTBOX = "OUTBOX";

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "NAME", length = 512)
	private String name;

	public IrisFolder() {
		this(null, "");
	}

	public IrisFolder(String name) {
		this(null, name);
	}

	public IrisFolder(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}