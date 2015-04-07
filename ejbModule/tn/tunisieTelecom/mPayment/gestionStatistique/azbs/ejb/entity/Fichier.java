package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Fichier
 *
 */
@Entity
public class Fichier implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_Fichier")
	@SequenceGenerator(name = "seq_Fichier", sequenceName = "SEQ_FICHIER", allocationSize = 1)
	private int id;
	private String nom;
	private Date date_traitement;
	private String etat_traitement;
	@ManyToOne
	private Banque banque = new Banque();
	@ManyToOne
	private User user = new User();
	@OneToMany
	private List<Transaction> transactions = new ArrayList<Transaction>();

	private static final long serialVersionUID = 1L;

	public Fichier() {
		super();
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDate_traitement() {
		return this.date_traitement;
	}

	public void setDate_traitement(Date date_traitement) {
		this.date_traitement = date_traitement;
	}

	public String getEtat_traitement() {
		return this.etat_traitement;
	}

	public void setEtat_traitement(String etat_traitement) {
		this.etat_traitement = etat_traitement;
	}

}
