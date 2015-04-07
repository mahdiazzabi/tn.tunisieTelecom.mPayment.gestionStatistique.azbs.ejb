package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Fichier;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.FichierEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.FichierEJBRemote;

/**
 * Session Bean implementation class FichierEJB
 */
@Stateless
@LocalBean
public class FichierEJB implements FichierEJBRemote, FichierEJBLocal {

	/**
	 * Default constructor.
	 */

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

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
		return entityManager.createQuery("SELECT f FROM fichier f ORDER BY date_traitement").getResultList();
	}

}
