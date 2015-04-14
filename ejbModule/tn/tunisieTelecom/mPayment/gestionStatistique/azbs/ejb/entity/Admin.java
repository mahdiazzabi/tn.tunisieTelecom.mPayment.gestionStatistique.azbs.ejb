package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.User;

/**
 * Entity implementation class for Entity: Admin
 *
 */
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}
   
}
