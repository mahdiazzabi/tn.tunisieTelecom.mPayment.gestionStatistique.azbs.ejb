package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Departement;

@Local
public interface DepartementEJBLocal {

	public List<Departement> findAll();

	public void add(Departement departement);

	public void update(Departement departement);

	public void delete(Departement departement);
	
	public Departement findById(int idDep);

}
