package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Produit;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.SousCategories;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.User;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.ProduitEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.ProduitEJBRemote;

/**
 * Session Bean implementation class ProduitEJB
 */
@Stateless
@LocalBean
public class ProduitEJB implements ProduitEJBRemote, ProduitEJBLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

    public ProduitEJB() { 	
        
    }

	@Override
	public void add(Produit produit) {
	
		entityManager.persist(produit);
	}

	@Override
	public void update(Produit produit) {
		entityManager.merge(produit);
		
	}

	@Override
	public void delete(Produit produit) {
		entityManager.remove(entityManager.merge(produit));
	}

	@Override
	public List<Produit> findall() {
		return entityManager.createQuery("SELECT p FROM Produit p",Produit.class).getResultList();
	}

	@Override
	public Produit findByLibelle(String libelle) {
		Query query = entityManager
				.createQuery(
						"SELECT p FROM produit p WHERE p.libelle =: libelle",
						Produit.class).setParameter("libelle", libelle);
		try {
			return (Produit) query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
}

	@Override
	public Produit findByref(Integer ref) {
		Query query = entityManager
				.createQuery(
						"SELECT p FROM produit p WHERE p.ref =: ref",
						Produit.class).setParameter("ref", ref);
		try {
			return (Produit) query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

}
