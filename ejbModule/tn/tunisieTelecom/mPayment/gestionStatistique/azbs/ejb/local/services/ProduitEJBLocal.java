package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Produit;

@Local
public interface ProduitEJBLocal {
	
	
	public void add (Produit produit);
	
	public void update (Produit produit);
	
	public void delete(Produit produit);
	
	public List<Produit> findall();
	
	
	

}
