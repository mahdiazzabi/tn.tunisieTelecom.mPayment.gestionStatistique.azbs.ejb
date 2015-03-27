package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.TransactionEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.TransactionEJBRemote;

/**
 * Session Bean implementation class TransactionEJB
 */
@Stateless
@LocalBean
public class TransactionEJB implements TransactionEJBRemote, TransactionEJBLocal {

    /**
     * Default constructor. 
     */
    public TransactionEJB() {
        // TODO Auto-generated constructor stub
    }

}
