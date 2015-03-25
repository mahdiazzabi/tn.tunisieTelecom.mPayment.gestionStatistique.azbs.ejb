package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.CategoriesLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.CategoriesRemote;

/**
 * Session Bean implementation class Categories
 */
@Singleton
@LocalBean
public class Categories implements CategoriesRemote, CategoriesLocal {

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public Categories() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories> findAll() {

		return entityManager
				.createQuery(
						"SELECT c FROM Categories c",
						tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories.class)
				.getResultList();
	}

	@Override
	public void add(
			tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories categories) {
		entityManager.persist(categories);

	}

	@Override
	public void update(
			tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories categories) {
		entityManager.merge(categories);

	}

	@Override
	public void delete(
			tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories categories) {
		entityManager.remove(entityManager.merge(categories));
	}

	@Override
	public tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories findById(
			int id) {
		return entityManager
				.find(tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories.class,
						id);
	}

}
