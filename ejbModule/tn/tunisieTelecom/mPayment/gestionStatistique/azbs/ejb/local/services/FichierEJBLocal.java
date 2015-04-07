package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import java.util.List;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.Fichier;

@Local
public interface FichierEJBLocal {

	public void add(Fichier fichier);

	public void remove(Fichier fichier);

	public void update(Fichier fichier);

	public Fichier findById(int id);

	public List<Fichier> findAll();
}