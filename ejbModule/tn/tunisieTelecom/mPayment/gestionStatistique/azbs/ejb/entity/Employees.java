package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.User;

/**
 * Entity implementation class for Entity: Employees
 *
 */
@Entity

public class Employees extends User implements Serializable {

	private String departement;
	private static final long serialVersionUID = 1L;

	public Employees() {
		super();
	}   
	public String getDepartement() {
		return this.departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
   
}
