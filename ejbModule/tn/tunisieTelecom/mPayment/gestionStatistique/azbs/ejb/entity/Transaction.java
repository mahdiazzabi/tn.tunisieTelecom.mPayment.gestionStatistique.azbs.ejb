package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.Double;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Transaction
 *
 */
@Entity
public class Transaction implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String id_transaction;
	private String tel_source;
	private String tel_benef;
	private String etat;
	private Double montant;
	private Date date;
	@ManyToOne
	private Produit produit =new Produit();
	@ManyToOne
	private Banque banque = new Banque();
	
	private static final long serialVersionUID = 1L;

	public Transaction() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getId_transaction() {
		return this.id_transaction;
	}

	public void setId_transaction(String id_transaction) {
		this.id_transaction = id_transaction;
	}   
	public String getTel_source() {
		return this.tel_source;
	}

	public void setTel_source(String tel_source) {
		this.tel_source = tel_source;
	}   
	public String getTel_benef() {
		return this.tel_benef;
	}

	public void setTel_benef(String tel_benef) {
		this.tel_benef = tel_benef;
	}   
	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}   
	public Double getMontant() {
		return this.montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}   
	public Date getDate() {
		return this.date;
	}
	

	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Banque getBanque() {
		return banque;
	}
	public void setBanque(Banque banque) {
		this.banque = banque;
	}
	public void setDate(Date date) {
		this.date = date;
	}
   
}
