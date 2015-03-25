package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.SousCategories;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.SousCategotiesEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.SousCategotiesEJBRemote;

/**
 * Session Bean implementation class SousCategotiesEJB
 */
@Singleton
@LocalBean
public class SousCategotiesEJB implements SousCategotiesEJBRemote,
		SousCategotiesEJBLocal {

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SousCategotiesEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(SousCategories sousCategories) {
		System.out.println("persist sous categories");
		entityManager.persist(sousCategories);
	}

	@Override
	public List<SousCategories> findAll() {
		return entityManager.createQuery("SELECT s FROM SousCategories s",SousCategories.class).getResultList();
	}

	@Override
	public void update(SousCategories sousCategories) {
		entityManager.merge(sousCategories);
	}

	@Override
	public void remove(SousCategories sousCategories) {
		entityManager.remove(entityManager.merge(sousCategories));
	}

}
