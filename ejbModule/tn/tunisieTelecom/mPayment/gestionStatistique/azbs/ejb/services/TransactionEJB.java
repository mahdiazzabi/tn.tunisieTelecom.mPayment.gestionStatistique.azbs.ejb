package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Banque;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.SousCategories;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Transaction;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.TransactionEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.TransactionEJBRemote;

/**
 * Session Bean implementation class TransactionEJB
 */
@Singleton
@LocalBean
public class TransactionEJB implements TransactionEJBRemote, TransactionEJBLocal {



	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;


    public TransactionEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addListe(List<Transaction> transactions) {
		
		for (Transaction transaction : transactions) {
		entityManager.persist(transaction);	
		}
		
		
	}




}
