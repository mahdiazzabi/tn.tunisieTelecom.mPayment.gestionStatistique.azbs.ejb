package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Produit
 *
 */
@Entity
public class Produit implements Serializable {

	   
	@Id
	@GeneratedValue(generator="seq_Prod")
	@SequenceGenerator(name="seq_Prod",sequenceName="SEQ_PRODUIT",allocationSize=1)
	private Integer id;
	private Integer ref ;
	private String libelle;
	@ManyToOne
	private SousCategories sousCategories = new SousCategories() ;
	@OneToMany(mappedBy="produit" )
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	
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
	public Integer getRef() {
		return ref;
	}
	public void setRef(Integer ref) {
		this.ref = ref;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
   
}
