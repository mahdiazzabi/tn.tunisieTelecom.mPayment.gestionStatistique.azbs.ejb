package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Departement;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.DepartementEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.DepartementEJBRemote;

/**
 * Session Bean implementation class DepartementEJB
 */
@Singleton
@LocalBean
public class DepartementEJB implements DepartementEJBRemote, DepartementEJBLocal {

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

    public DepartementEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Departement> findAll() {
	
		return entityManager.createQuery("SELECT d FROM Departement d", Departement.class).getResultList() ;
	}

	@Override
	public void add(Departement departement) {
		entityManager.persist(departement);
	}

	@Override
	public void update(Departement departement) {
		entityManager.merge(departement);
		
	}

	@Override
	public void delete(Departement departement) {
		entityManager.remove(entityManager.merge(departement));
		
	}

	@Override
	public Departement findById(int idDep) {
		
		return entityManager.find(Departement.class, idDep);
	}

}
