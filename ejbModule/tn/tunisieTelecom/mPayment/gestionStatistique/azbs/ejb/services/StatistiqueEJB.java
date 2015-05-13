package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Statistique;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.StatistiqueEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.StatistiqueEJBRemote;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.util.EtatSousCategorie;

/**
 * Session Bean implementation class StatistiqueEJB
 */
@Singleton
@LocalBean
public class StatistiqueEJB implements StatistiqueEJBRemote,
		StatistiqueEJBLocal {

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

	public StatistiqueEJB() {

	}

	@Override
	public List<Object[]> statistiquesCategories(Date start, Date end, int idCat) {

		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT s.journee, SUM(s.montantTransactions) FROM Statistique s WHERE s.journee BETWEEN :start AND :end AND s.sousCategories.categories.id =:idCat GROUP BY s.journee",
						Object[].class).setParameter("start", start)
				.setParameter("end", end).setParameter("idCat", idCat);

		List<Object[]> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public void add(Statistique statistique) {
		entityManager.persist(statistique);
	}

	@Override
	public List<Statistique> findByIdBanqueJour(Date date, int idBanque) {

		return entityManager
				.createQuery(
						"SELECT s FROM Statistique s WHERE s.journee=:date AND s.banque.id=:idBanque",
						Statistique.class).setParameter("date", date)
				.setParameter("idBanque", idBanque).getResultList();
	}

	@Override
	public void delete(Statistique statistique) {
		entityManager.remove(entityManager.merge(statistique));
	}

	@Override
	public List<Object[]> statSousCat(Date start, Date end, int idSousCat) {

		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT s.journee , SUM(s.montantTransactions) FROM Statistique s WHERE s.journee BETWEEN :start AND :end AND s.sousCategories.id=:idSousCat GROUP BY s.journee",
						Object[].class).setParameter("start", start)
				.setParameter("end", end).setParameter("idSousCat", idSousCat);
		List<Object[]> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public List<Object[]> statSousCat(Date start, Date end, int idSousCat,
			int idBanque) {

		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT s.journee ,SUM(s.montantTransactions), SUM(s.nbrTransactions) FROM Statistique s WHERE s.journee BETWEEN :start AND :end AND s.sousCategories.id=:idSousCat AND banque_id=:idBanque GROUP BY s.journee",
						Object[].class).setParameter("start", start)
				.setParameter("end", end).setParameter("idSousCat", idSousCat)
				.setParameter("idBanque", idBanque);
		List<Object[]> resultList = q.getResultList();
		return resultList;

	}

	@Override
	public Object[] statistiquesBanques(Date start, Date end, int idBanque) {
		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT SUM(s.montantTransactions), SUM(s.nbrTransactions) FROM Statistique s WHERE s.journee BETWEEN :start AND :end AND banque_id=:idBanque",
						Object[].class).setParameter("start", start)
				.setParameter("end", end).setParameter("idBanque", idBanque);
		return q.getSingleResult();
	}

	@Override
	public List<Object[]> statistiquesGlobaleBanques(Date start, Date end,
			int idBanque) {
		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT s.journee , SUM(s.montantTransactions), SUM(s.nbrTransactions) FROM Statistique s WHERE s.journee BETWEEN :start AND :end AND banque_id=:idBanque GROUP BY s.journee",
						Object[].class).setParameter("start", start)
				.setParameter("end", end).setParameter("idBanque", idBanque);
		List<Object[]> resultList = q.getResultList();
		return resultList;
	}

}
