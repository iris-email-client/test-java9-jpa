module iris {
	requires java.naming;
	requires java.sql;		
	requires java.xml.bind;
	
	requires hibernate.core;
	requires hibernate.jpa;	
	requires javassist;	
		
	exports br.unb.cic.iris.model;
	exports br.unb.cic.iris.persistence.jpa;
}