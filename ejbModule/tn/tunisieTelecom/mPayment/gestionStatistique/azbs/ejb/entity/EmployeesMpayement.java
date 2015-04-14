package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.User;

/**
 * Entity implementation class for Entity: EmployeesMpayement
 *
 */
@Entity

public class EmployeesMpayement extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public EmployeesMpayement() {
		super();
	}
   
}
