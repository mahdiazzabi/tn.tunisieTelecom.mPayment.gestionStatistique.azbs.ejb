package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Produit
 *
 */
@Entity

public class Produit implements Serializable {

	   
	@Id
	private Integer id;
	
	private String libelle;
	@ManyToOne
	private SousCategories sousCategories = new SousCategories() ;
	
	public SousCategories getCategories() {
		return sousCategories;
	}
	public void setCategories(SousCategories categories) {
		this.sousCategories = categories;
	}

	private static final long serialVersionUID = 1L;

	public Produit() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public SousCategories getSousCategories() {
		return sousCategories;
	}
	public void setSousCategories(SousCategories sousCategories) {
		this.sousCategories = sousCategories;
	}
   
}
