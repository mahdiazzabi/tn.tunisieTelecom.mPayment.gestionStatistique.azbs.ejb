package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Categories
 *
 */
@Entity
public class Categories implements Serializable {

	@Id
	@GeneratedValue(generator="seq_Cat")
	@SequenceGenerator(name="seq_Cat",sequenceName="SEQ_CAT",allocationSize=1)
	private int id;
	private String libelle;
	@OneToMany(fetch = FetchType.EAGER , mappedBy="categories")
	private List<SousCategories> sousCategories = new ArrayList<SousCategories>();
	private static final long serialVersionUID = 1L;

	public Categories() {
		super();
	}
	public List<SousCategories> getSousCategories() {
		return sousCategories;
	}

	public void setSousCategories(List<SousCategories> sousCategories) {
		this.sousCategories = sousCategories;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle.toUpperCase();
	}

}
