package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Banque;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Transaction;

@Local
public interface BanqueEJBLocal {
	
	
	public void add(Banque banque);
	
	public void update(Banque banque);
	
	public void delete(Banque banque);
	
	public Banque findById(int id);
	
	public List<Banque> findAll();
	
	

}
