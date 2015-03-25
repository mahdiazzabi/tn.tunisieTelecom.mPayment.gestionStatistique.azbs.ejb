package tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.entity.User;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.local.services.UserEJBLocal;
import tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb.services.remote.UserEJBRemote;

/**
 * Session Bean implementation class UserEJB
 */
@Singleton
@LocalBean
public class UserEJB implements UserEJBRemote, UserEJBLocal {

	@PersistenceContext(name = "tn.tunisieTelecom.mPayment.gestionStatistique.azbs.ejb")
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserEJB() {

	}

	@Override
	public User authentification(String login, String mdp) {
		Query query = entityManager
				.createQuery(
						"SELECT u FROM utilisateur u WHERE u.login=:login AND u.password =:password",
						User.class).setParameter("login", login)
				.setParameter("password", mdp);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			System.err.println("User not found" + e.getMessage());
			return null;
		}

	}

}
