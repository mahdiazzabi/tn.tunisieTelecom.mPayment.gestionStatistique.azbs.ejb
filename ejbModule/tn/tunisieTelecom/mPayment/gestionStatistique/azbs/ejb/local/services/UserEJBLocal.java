package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services;

import javax.ejb.Local;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.User;

@Local
public interface UserEJBLocal {
	
	public User doLogin(String login,String mdp);
	
	public void add (User user);
	
	public void update (User user);
	
	public void delete (User user);
	
	
	
}
