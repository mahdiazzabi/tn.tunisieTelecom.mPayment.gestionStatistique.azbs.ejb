package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Fichier;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.FichierEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.FichierEJBRemote;

/**
 * Session Bean implementation class FichierEJB
 */
@Singleton
@LocalBean
public class FichierEJB implements FichierEJBRemote, FichierEJBLocal {

	/**
	 * Default constructor.
	 */

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

	@EJB
	BanqueEJB banqueEJB ;
	
	public FichierEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(Fichier fichier) {
		entityManager.persist(fichier);
	}

	@Override
	public void remove(Fichier fichier) {
		entityManager.remove(entityManager.merge(fichier));
	}

	@Override
	public void update(Fichier fichier) {
		entityManager.merge(fichier);

	}

	@Override
	public Fichier findById(int id) {
		return entityManager.find(Fichier.class, id);
	}

	@Override
	public List<Fichier> findAll() {
		return entityManager.createQuery(
				"SELECT f FROM Fichier f ORDER BY date_traitement desc",
				Fichier.class).getResultList();
	}

	@Override
	public Fichier findByNom(String nom) {
		Query query = entityManager.createQuery(
				"SELECT f FROM Fichier f WHERE f.nom=:nom", Fichier.class)
				.setParameter("nom", nom);
		try {
			return (Fichier) query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());

			return null;
		}

	}

	@Override
	public Boolean verif_traitement_for_stat(Date start, Date end, int idBanque) {
		try {

			List<Fichier> fichiers = entityManager
					.createQuery(
							"SELECT f FROM Fichier f WHERE f.banque.id =:idBanque AND f.date_fichier BETWEEN :start AND :end",
							Fichier.class).setParameter("start", start)
					.setParameter("end", end)
					.setParameter("idBanque", idBanque).getResultList();
			System.err
					.println("-----------------------------------------------");
			final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
			long diff = Math.abs(end.getTime() - start.getTime());
			long numberOfDay = ((long) diff / MILLISECONDS_PER_DAY) + 1;
			System.err.println(fichiers.size());
			if (fichiers.size() < numberOfDay) {
				return false;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public Boolean verif_traitement_for_stat(Date start, Date end) {
		try {
			int nbr_banque = banqueEJB.findAll().size() ;
			List<Fichier> fichiers = entityManager
					.createQuery(
							"SELECT f FROM Fichier f WHERE f.date_fichier BETWEEN :start AND :end ",
							Fichier.class).setParameter("start", start)
					.setParameter("end", end).getResultList();
			final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
			long diff = Math.abs(end.getTime() - start.getTime());
			long numberOfDay = ((long) diff / MILLISECONDS_PER_DAY) + 1;
			System.err.println(fichiers.size());
			if ((fichiers.size() / numberOfDay) < nbr_banque) {
				return false;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}

}
