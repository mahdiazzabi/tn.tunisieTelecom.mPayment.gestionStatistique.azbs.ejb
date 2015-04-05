package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.omg.CORBA.PRIVATE_MEMBER;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Transaction;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.TransactionEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.TransactionEJBRemote;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.util.Etat;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.util.EtatSousCategorie;

/**
 * Session Bean implementation class TransactionEJB
 */
@Singleton
@LocalBean
public class TransactionEJB implements TransactionEJBRemote,
		TransactionEJBLocal {

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

	public TransactionEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addListe(List<Transaction> transactions) {
		int i = 0;
		try {
			for (Transaction transaction : transactions) {
				entityManager.persist(transaction);
				System.err.println("OK TRANSACTION  :" + i++);
				entityManager.flush();
				entityManager.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add(Transaction transaction) {
		entityManager.persist(transaction);
	}

	@Override
	public List<Etat> calculEtat(Date start, Date end, int id) {
		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT c.libelle,(SELECT count(t) as nbr FROM Transaction t WHERE t.banque.id =:id AND t.produit.sousCategories.categories.id=c.id AND t.date BETWEEN :start AND :end) as nbr,(SELECT sum(t.montant) as somme FROM Transaction t WHERE t.banque.id =:id AND t.produit.sousCategories.categories.id=c.id AND t.date BETWEEN :start AND :end) as somme, c.id FROM Categories c GROUP BY c.libelle",
						Object[].class).setParameter("id", id)
				.setParameter("start", start).setParameter("end", end);
		List<Object[]> resultList = q.getResultList();
		List<Etat> etats = new ArrayList<Etat>();
		Etat etat;
		for (Object[] objects : resultList) {
			etat = new Etat();
			etat.setLibelle((String) objects[0]);
			etat.setNbr(Integer.parseInt("" + objects[1]));

			if (objects[2] == null)
				etat.setSomme(0);
			else
				etat.setSomme(Float.parseFloat("" + objects[2]));

			etat.setId(Integer.parseInt(objects[3] + ""));
			etats.add(etat);
		}
		return etats;
	}

	@Override
	public List<EtatSousCategorie> calculeEtatSousCategorie(Date start,
			Date end, int id_banque) {
		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT c.libelle,(SELECT count(t) as nbr FROM Transaction t WHERE t.banque.id =:id AND t.produit.sousCategories.id=c.id AND t.date BETWEEN :start AND :end) as nbr,(SELECT sum(t.montant) as somme FROM Transaction t WHERE t.banque.id =:id AND t.produit.sousCategories.id=c.id AND t.date BETWEEN :start AND :end) as somme FROM SousCategories c GROUP BY c.libelle",
						Object[].class).setParameter("id", id_banque)
				.setParameter("start", start).setParameter("end", end);
		List<Object[]> resultList = q.getResultList();
		List<EtatSousCategorie> etatSousCategories = new ArrayList<EtatSousCategorie>();
		EtatSousCategorie etat;
		for (Object[] objects : resultList) {
			etat = new EtatSousCategorie();
			etat.setLibelle((String) objects[0]);
			etat.setNbr(Integer.parseInt("" + objects[1]));

			if (objects[2] == null)
				etat.setSomme(0);
			else
				etat.setSomme(Float.parseFloat("" + objects[2]));

			etatSousCategories.add(etat);
		}
		return etatSousCategories;
	}

}
