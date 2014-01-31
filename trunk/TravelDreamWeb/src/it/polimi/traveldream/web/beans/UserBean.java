package it.polimi.traveldream.web.beans;

import java.io.Serializable;

import it.polimi.traveldream.ejb.UsrMgr;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private UsrMgr userMgr;

	public String getName() {
		return userMgr.getUserDTO().getFirstName();
	}
}
