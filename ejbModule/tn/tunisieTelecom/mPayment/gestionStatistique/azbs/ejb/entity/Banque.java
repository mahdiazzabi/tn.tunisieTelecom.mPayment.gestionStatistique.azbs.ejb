package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Banque
 *
 */
@Entity
public class Banque implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_Banq")
	@SequenceGenerator(name = "seq_Banq", sequenceName = "SEQ_BANQUE", allocationSize = 1)
	private int id;
	private String nom;

	@OneToMany(mappedBy = "banque")
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
//	@OneToMany(mappedBy = "banque")
//	private List<Fichier> fichiers = new ArrayList<Fichier>();

	private static final long serialVersionUID = 1L;

	public Banque() {
		super();
	}

//	public List<Fichier> getFichiers() {
//		return fichiers;
//	}
//
//	public void setFichiers(List<Fichier> fichiers) {
//		this.fichiers = fichiers;
//	}

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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
