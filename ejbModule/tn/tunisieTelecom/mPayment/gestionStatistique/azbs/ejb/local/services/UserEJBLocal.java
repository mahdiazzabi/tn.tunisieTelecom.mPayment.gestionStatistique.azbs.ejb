package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.User;

@Local
public interface UserEJBLocal {
	public User authentification(String login,String mdp);
}
