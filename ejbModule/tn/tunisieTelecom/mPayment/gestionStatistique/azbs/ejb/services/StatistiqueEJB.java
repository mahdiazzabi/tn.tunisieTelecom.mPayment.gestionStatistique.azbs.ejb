package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

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
	public List<Object[]> statistiquesCategories(Date start, Date end,
			int idCat) {

		TypedQuery<Object[]> q = entityManager
				.createQuery(
						"SELECT s.journee, SUM(s.montantTransactions) FROM Statistique s WHERE s.journee BETWEEN :start AND :end AND s.sousCategories.categories.id =:idCat GROUP BY s.journee",
						Object[].class).setParameter("start", start)
				.setParameter("end", end).setParameter("idCat", idCat);

		List<Object[]> resultList = q.getResultList();
		return resultList;
	}

}
