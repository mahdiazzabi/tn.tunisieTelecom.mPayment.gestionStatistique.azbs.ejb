package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Departement
 *
 */
@Entity
public class Departement implements Serializable {

	@Id
	@GeneratedValue(generator = "seq_dep")
	@SequenceGenerator(name = "seq_dep", sequenceName = "SEQ_DEPARTEMENT", allocationSize = 1)
	private int id;
	private String nom;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "departement")
	private List<Employees> employees = new ArrayList<Employees>();
	public Departement() {
		super();
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
	public List<Employees> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}
   
}
