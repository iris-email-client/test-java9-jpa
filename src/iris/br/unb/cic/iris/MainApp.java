package br.unb.cic.iris;

import java.util.List;

import br.unb.cic.iris.model.IrisFolder;

public class MainApp {	

	static void novoTeste() {
		System.out.println("SALVANDO ............");
		IrisFolder folder = new IrisFolder("INBOX123");
		new br.unb.cic.iris.persistence.jpa.FolderDAO().persist(folder);
		System.out.println("SALVOU ............");

		System.out.println("LISTANDO ............");
		List<IrisFolder> list = new br.unb.cic.iris.persistence.jpa.FolderDAO().findAll();
		list.forEach(f -> System.out.println(f.getName()));
	}

	public static void main(String[] args) {
		System.out.println("INICIANDO .......................");
		// HibernateUtil.getSessionFactory();

		// testJPA();
		novoTeste();

		// br.unb.cic.iris.persistence.relational.HibernateUtil.getSessionFactory();
		/*
		 * try { testIRIS(); } catch (EmailException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

	}
}
