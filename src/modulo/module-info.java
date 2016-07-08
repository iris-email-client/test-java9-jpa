module modulo {
	requires java.naming;
	requires java.sql;		
	requires java.xml.bind;
	
	requires hibernate.core;
	requires hibernate.jpa;
	requires javassist;	
	
	exports teste;
	exports br.unb.cic.iris.model;
	exports br.unb.cic.iris.persistence.relational;
}