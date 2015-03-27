package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Banque;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.BanqueEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.BanqueEJBRemote;

/**
 * Session Bean implementation class BanqueEJB
 */
@Stateless
@LocalBean
public class BanqueEJB implements BanqueEJBRemote, BanqueEJBLocal {

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public BanqueEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void add(Banque banque) {
		entityManager.persist(banque);
	}

	@Override
	public void update(Banque banque) {
		entityManager.merge(banque);
	}

	@Override
	public void delete(Banque banque) {
		entityManager.remove(entityManager.merge(banque));
	}

	@Override
	public List<Banque> findAll() {
		
		return entityManager.createQuery("SELECT b FROM Banque b", Banque.class).getResultList();
	}

	
}
