package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: SousCategories
 *
 */
@Entity
public class SousCategories implements Serializable {

	@Id
	@GeneratedValue(generator="seq_Sous_Cat")
	@SequenceGenerator(name="seq_Sous_Cat",sequenceName="SEQ_SOUS_CAT",allocationSize=1)
	private int id;
	private String libelle;
	@ManyToOne
	private Categories categories = new Categories();
	@OneToMany(mappedBy="sousCategories")
	private List<Produit> produits = new ArrayList<Produit>();
	
	private static final long serialVersionUID = 1L;

	public SousCategories() {
		super();
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
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
		this.libelle = libelle;
	}

}
