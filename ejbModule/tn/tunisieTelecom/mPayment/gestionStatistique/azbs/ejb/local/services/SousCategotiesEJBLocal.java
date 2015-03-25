package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.SousCategories;

@Local
public interface SousCategotiesEJBLocal {
	public void add(SousCategories sousCategories);

	public List<SousCategories> findAll();

	public void update(SousCategories sousCategories);

	public void remove(SousCategories sousCategories);
}
