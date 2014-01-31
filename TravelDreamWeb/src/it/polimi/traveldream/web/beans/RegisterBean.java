package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.UsrMgr;
import it.polimi.traveldream.entities.UserDTO;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UsrMgr userMgr;

	private UserDTO user;

	public RegisterBean() {
		user = new UserDTO();
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String register() {
		userMgr.save(user);
		return "homepage?faces-redirect=true";
	}
}
