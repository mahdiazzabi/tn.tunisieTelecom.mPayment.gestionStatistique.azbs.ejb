package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Statistique;

@Local
public interface StatistiqueEJBLocal {
	
	
	
	public List<Object[]> statistiquesCategories(Date start , Date end , int idCat);

}
