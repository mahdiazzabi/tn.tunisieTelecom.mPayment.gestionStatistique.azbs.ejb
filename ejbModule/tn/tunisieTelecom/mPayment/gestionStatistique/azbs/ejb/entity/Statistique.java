package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Statistique
 *
 */
@Entity
public class Statistique implements Serializable {

	@Id
	@GeneratedValue(generator="seq_Stat")
	@SequenceGenerator(name="seq_Stat",sequenceName="SEQ_STAT",allocationSize=1)
	private int id;
	private Date journee ;
	private int nbrTransactions;
	private float montantTransactions;
	@ManyToOne
	private SousCategories sousCategories = new SousCategories();

	@ManyToOne
	private Banque banque = new Banque();

	private static final long serialVersionUID = 1L;

	public Statistique() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbrTransactions() {
		return this.nbrTransactions;
	}

	public void setNbrTransactions(int nbrTransactions) {
		this.nbrTransactions = nbrTransactions;
	}

	public float getMontantTransactions() {
		return this.montantTransactions;
	}

	public void setMontantTransactions(float montantTransactions) {
		this.montantTransactions = montantTransactions;
	}

	public SousCategories getSousCategories() {
		return sousCategories;
	}

	public void setSousCategories(SousCategories sousCategories) {
		this.sousCategories = sousCategories;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public Date getJournee() {
		return journee;
	}

	public void setJournee(Date journee) {
		this.journee = journee;
	}

	
}
