package it.polimi.traveldream.ejb;



import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.ejb.client.UsrMgr;
import it.polimi.traveldream.entities.Group;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;
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
	

	public void save(UserDTO userDTO) {
		User user = new User();
		user= userDTOToUser(userDTO);
		List<Group> groups = new ArrayList<Group>();
		groups.add(Group.USER);
		user.setGroups(groups);
		em.persist(user);
	}


	@RolesAllowed({Group._USER,Group._ADMIN})
	public void update(UserDTO userDTO) {
		User user = new User();
		user= userDTOToUser(userDTO);
		em.merge(user);
	}


	@RolesAllowed({Group._USER,Group._ADMIN})
	public UserDTO getUserDTO() {
		UserDTO userDTO = userToUserDTO(getPrincipalUser());
		return userDTO;
	}


	@RolesAllowed({Group._USER})
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

public User userDTOToUser(UserDTO userDTO) {
		
	PacchettoPersonalizzatoBean pacchettopersremote= new PacchettoPersonalizzatoBean();
	
		User user= new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getEmail());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setRegisteredOn(userDTO.getRegisteredOn());
		
		List<PacchettoPersonalizzato> pacchettiCliente= new ArrayList<PacchettoPersonalizzato>();
		for(int i=0;i<userDTO.getPacchettiCliente().size(); i++){
			pacchettiCliente.add(pacchettopersremote.pacchettoPersonalizzatoDTOToPacchettoPersonalizzato(userDTO.getPacchettiCliente().get(i)));
		}
		user.setPacchettiCliente(pacchettiCliente);
		
		List<PacchettoPersonalizzato> giftList= new ArrayList<PacchettoPersonalizzato>();
		for(int i=0;i<userDTO.getGiftList().size(); i++){
			pacchettiCliente.add(pacchettopersremote.pacchettoPersonalizzatoDTOToPacchettoPersonalizzato(userDTO.getGiftList().get(i)));
		}
		user.setGiftList(giftList);
		
		return user;
		
		
		
	}

public UserDTO userToUserDTO(User user) {
		
	
		PacchettoPersonalizzatoBean pacchettopersremote= new PacchettoPersonalizzatoBean();
		UserDTO userDTO= new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getEmail());
		userDTO.setLastName(user.getLastName());
		userDTO.setPassword(user.getPassword());
		userDTO.setRegisteredOn(user.getRegisteredOn());
		
		List<PacchettoPersonalizzatoDTO> pacchettiCliente= new ArrayList<PacchettoPersonalizzatoDTO>();
		for(int i=0;i<user.getPacchettiCliente().size(); i++){
			pacchettiCliente.add(pacchettopersremote.pacchettoPersonalizzatoToDTO(user.getPacchettiCliente().get(i)));
		}
		userDTO.setPacchettiCliente(pacchettiCliente);;
		
		List<PacchettoPersonalizzatoDTO> giftList= new ArrayList<PacchettoPersonalizzatoDTO>();
		for(int i=0;i<user.getGiftList().size(); i++){
			pacchettiCliente.add(pacchettopersremote.pacchettoPersonalizzatoToDTO(user.getGiftList().get(i)));
		}
		userDTO.setGiftList(giftList);
		
		return userDTO;
		
		
		
	}	






	
}
