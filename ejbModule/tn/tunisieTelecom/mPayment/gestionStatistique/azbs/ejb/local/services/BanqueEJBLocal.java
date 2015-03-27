package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Banque;

@Local
public interface BanqueEJBLocal {
	
	
	public void add(Banque banque);
	
	public void update(Banque banque);
	
	public void delete(Banque banque);
	
	public List<Banque> findAll();
	
	

}
