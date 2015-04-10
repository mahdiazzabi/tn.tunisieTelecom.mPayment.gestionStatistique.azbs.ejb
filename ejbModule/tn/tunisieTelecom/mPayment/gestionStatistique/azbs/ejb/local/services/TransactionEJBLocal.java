package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Banque;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Transaction;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.util.Etat;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.util.EtatSousCategorie;

@Local
public interface TransactionEJBLocal {

	public boolean addListe(List<Transaction> transactions);

	public void add(Transaction transaction);

	public List<Etat> calculEtat(Date start, Date end, int id);

	public List<EtatSousCategorie> calculeEtatSousCategorie(Date start,Date end,int id_banque);
	
	
	public Double calculStatAllBanques(Date start, Date end); 
}
