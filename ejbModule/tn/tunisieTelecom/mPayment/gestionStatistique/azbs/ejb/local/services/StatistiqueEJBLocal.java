package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Statistique;

@Local
public interface StatistiqueEJBLocal {
	
	
	
	public void add(Statistique statistique);
	
	public void delete(Statistique statistique);
	
	public List<Statistique> findByIdBanqueJour(Date date , int idBanque);
	
	public List<Object[]> statSousCat(Date start , Date end , int idSousCat);
	
	public List<Object[]> statSousCat(Date start , Date end , int idSousCat , int idBanque);
	
	public List<Object[]> statistiquesCategories(Date start , Date end , int idCat);
	
	public Object[] statistiquesBanques(Date start , Date end , int idBanque );
	
	public List<Object[]> statistiquesGlobaleBanques(Date start , Date end , int idBanque);
	
	

}
