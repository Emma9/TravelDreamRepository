package it.polimi.traveldream.ejb;



import it.polimi.traveldream.entities.Group;
import it.polimi.traveldream.entities.User;
import it.polimi.traveldream.entities.UserDTO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UsrMgrBean
 */
@Stateless
public class UsrMgrBean implements UsrMgr {
	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;
	

	public void save(UserDTO user) {
		User newUser = new User(user);
		List<Group> groups = new ArrayList<Group>();
		groups.add(Group.CLIENTI);
		newUser.setGroups(groups);
		em.persist(newUser);
	}


	@RolesAllowed({Group._CLIENTE,Group._IMPIEGATO})
	public void update(UserDTO user) {
		em.merge(new User(user));
	}


	@RolesAllowed({Group._CLIENTE,Group._IMPIEGATO})
	public UserDTO getUserDTO() {
		UserDTO userDTO = convertToDTO(getPrincipalUser());
		return userDTO;
	}


	@RolesAllowed({Group._CLIENTE})
	public void unregister() {
		remove(getPrincipalUser());
	}


	public User find(String email) {
    	return em.find(User.class, email);
    }
    
    public List<User> getAllUsers() {
    	return em.createNamedQuery(User.FIND_ALL, User.class)
                .getResultList();
    }

    public void remove(String email) {
		User user = find(email);
        em.remove(user);
	}
    
    public void remove(User user) {
    	em.remove(user);
	}
    
    
    public User getPrincipalUser() {
    	return find(getPrincipalEmail());
    }
	
    
    public String getPrincipalEmail() {
    	return context.getCallerPrincipal().getName();
    }

    private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		return userDTO;
	}



}
