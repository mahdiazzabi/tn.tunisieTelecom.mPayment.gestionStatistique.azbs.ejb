package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.ArrayList;
import java.util.Calendar;
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

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Banque;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.SousCategories;
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
	public boolean addListe(List<Transaction> transactions) {
		try {
			for (Transaction transaction : transactions) {
				entityManager.persist(transaction);
				entityManager.flush();
				entityManager.clear();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void add(Transaction transaction) {
		entityManager.persist(transaction);
	}

	@Override
	public List<Etat> calculEtat(Date start, Date end, int id) {
		String succes = "S";
		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT c.libelle,(SELECT count(t) as nbr FROM Transaction t WHERE t.fichier.banque.id =:id AND t.etat =:succes AND t.produit.sousCategories.categories.id=c.id AND t.date BETWEEN :start AND :end) as nbr,(SELECT sum(t.montant) as somme FROM Transaction t WHERE t.fichier.banque.id =:id AND  t.etat =:succes AND t.produit.sousCategories.categories.id=c.id AND t.date BETWEEN :start AND :end) as somme, c.id FROM Categories c GROUP BY c.libelle",
						Object[].class).setParameter("id", id)
				.setParameter("succes", succes).setParameter("start", start)
				.setParameter("end", end);
		List<Object[]> resultList = q.getResultList();
		List<Etat> etats = new ArrayList<Etat>();
		Etat etat;
		for (Object[] objects : resultList) {
			etat = new Etat();
			etat.setLibelle((String) objects[0]);
			System.err.println(etat.getLibelle() + (String) objects[0]);
			if (objects[2] == null)
				etat.setNbr(0);
			else
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
		String succes = "S";
		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT c.libelle,(SELECT count(t) as nbr FROM Transaction t WHERE t.fichier.banque.id =:id AND t.etat =:succes AND t.produit.sousCategories.id=c.id AND t.date BETWEEN :start AND :end) as nbr,(SELECT sum(t.montant) as somme FROM Transaction t WHERE t.fichier.banque.id =:id AND t.etat =:succes AND t.produit.sousCategories.id=c.id AND t.date BETWEEN :start AND :end) as somme FROM SousCategories c GROUP BY c.libelle",
						Object[].class).setParameter("id", id_banque)
				.setParameter("succes", succes).setParameter("start", start)
				.setParameter("end", end);
		List<Object[]> resultList = q.getResultList();
		List<EtatSousCategorie> etatSousCategories = new ArrayList<EtatSousCategorie>();
		EtatSousCategorie etat;
		for (Object[] objects : resultList) {
			etat = new EtatSousCategorie();
			etat.setLibelle((String) objects[0]);
			if (objects[2] == null)
				etat.setNbr(0);
			else
				etat.setNbr(Integer.parseInt("" + objects[1]));

			if (objects[2] == null)
				etat.setSomme(0);
			else
				etat.setSomme(Float.parseFloat("" + objects[2]));

			etatSousCategories.add(etat);
		}
		return etatSousCategories;
	}

	@Override
	public Object[] calculStatBanque(Date start, Date end, int idBanque) {
		String succes = "S";
		try {
			TypedQuery<Object[]> q = entityManager
					.createQuery(
							"SELECT count(t) as nbr , sum(t.montant) as somme FROM Transaction t WHERE t.fichier.banque.id =:id AND  t.etat =:succes AND t.date BETWEEN :start AND :end",
							Object[].class).setParameter("start", start)
					.setParameter("end", end).setParameter("id", idBanque)
					.setParameter("succes", succes);
			return q.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Object[] calculStatBanqueSousCat(Date start, Date end, int idBanque,
			int idSousCat) {
		String succes = "S";
		try {
			TypedQuery<Object[]> q = entityManager
					.createQuery(
							"SELECT count(t) as nbr , sum(t.montant) as somme FROM Transaction t WHERE t.fichier.banque.id =:idBanque AND  t.etat =:succes AND t.date BETWEEN :start AND :end AND t.produit.sousCategories.id =:idSousCat",
							Object[].class).setParameter("start", start)
					.setParameter("end", end)
					.setParameter("idBanque", idBanque)
					.setParameter("succes", succes)
					.setParameter("idSousCat", idSousCat);
			return q.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public double calculStatMensuelle(Date start, int idSouscat) {
		String succes = "S";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(calendar.HOUR_OF_DAY, +23);
		calendar.add(calendar.MINUTE, 59);
		calendar.add(calendar.SECOND, 59);
		Date end = calendar.getTime();
		try {
			TypedQuery<Object[]> q = entityManager
					.createQuery(
							"SELECT count(t) as nbr, sum(t.montant) as somme FROM Transaction t WHERE t.etat =:succes AND t.date BETWEEN :start AND :end AND t.produit.sousCategories.id =:idSouscat",
							Object[].class).setParameter("start", start)
					.setParameter("end", end).setParameter("succes", succes)
					.setParameter("idSouscat", idSouscat);
			Object[] object = q.getSingleResult();
			if (object[1] == null) {
				return 0.0;
			} else {
				System.err.println(Double.parseDouble("" + object[1]));
				return Double.parseDouble("" + object[1]);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return 0.0;
		}
	}

	@Override
	public List<Etat> calculEtatAllBanques(Date start, Date end) {
		String succes = "S";
		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT b.nom , (SELECT COUNT(t) FROM Transaction t WHERE t.fichier.banque.id = b.id AND t.etat =:succes AND t.date BETWEEN :start AND :end ) ,(SELECT SUM(t.montant) FROM Transaction t WHERE t.fichier.banque.id = b.id AND t.etat =:succes AND t.date BETWEEN :start AND :end), b.id FROM Banque b GROUP BY b.nom",
						Object[].class).setParameter("succes", succes)
				.setParameter("start", start).setParameter("end", end);
		List<Object[]> resultList = q.getResultList();
		List<Etat> etats = new ArrayList<Etat>();
		Etat etat;
		for (Object[] objects : resultList) {
			etat = new Etat();
			etat.setLibelle((String) objects[0]);
			System.err.println(etat.getLibelle() + (String) objects[0]);
			if (objects[2] == null)
				etat.setNbr(0);
			else
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
}
