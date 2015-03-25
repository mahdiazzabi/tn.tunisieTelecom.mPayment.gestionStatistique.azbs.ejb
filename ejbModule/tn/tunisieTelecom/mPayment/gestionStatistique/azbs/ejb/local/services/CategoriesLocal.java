package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Categories;

@Local
public interface CategoriesLocal {
	public List<Categories> findAll();

	public void add(Categories categories);
	
	public void update(Categories categories);
	
	public void delete(Categories categories);
}
