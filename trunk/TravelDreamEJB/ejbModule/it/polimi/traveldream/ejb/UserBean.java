package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.UserBeanRemote;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
public class UserBean implements UserBeanRemote {
	
	
	@PersistenceContext(unitName = "travelDream_project")
	private EntityManager manager;

    /**
     * Default constructor. 
     */
    public UserBean() {
        // TODO Auto-generated constructor stub
    }

}
