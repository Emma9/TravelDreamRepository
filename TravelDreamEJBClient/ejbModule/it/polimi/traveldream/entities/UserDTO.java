package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "User.findAll";

	@Id
	private String email;

	private String firstName;

	private String lastName;

	private String password; // sha-512 + hex
	
	private List<PacchettoPersonalizzatoDTO> pacchettiCliente = new ArrayList<PacchettoPersonalizzatoDTO>(0);
	
	private List<PacchettoPersonalizzatoDTO> giftList = new ArrayList<PacchettoPersonalizzatoDTO>(0);

	
	

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date registeredOn;

	@ElementCollection(targetClass = GroupDTO.class)
	@CollectionTable(name = "USERS_GROUPS", joinColumns = @JoinColumn(name = "email"))
	@Enumerated(EnumType.STRING)
	@Column(name = "groupname")
	private List<GroupDTO> groups;

	public UserDTO() {
		super();
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password in SHA512 HEX representation
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	

	/**
	 * @return the pacchettiCliente
	 */
	public List<PacchettoPersonalizzatoDTO> getPacchettiCliente() {
		return pacchettiCliente;
	}

	/**
	 * @param pacchettiCliente the pacchettiCliente to set
	 */
	public void setPacchettiCliente(List<PacchettoPersonalizzatoDTO> pacchettiCliente) {
		this.pacchettiCliente = pacchettiCliente;
	}

	/**
	 * @return the giftList
	 */
	public List<PacchettoPersonalizzatoDTO> getGiftList() {
		return giftList;
	}

	/**
	 * @param giftList the giftList to set
	 */
	public void setGiftList(List<PacchettoPersonalizzatoDTO> giftList) {
		this.giftList = giftList;
	}

	public Date getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	public List<GroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDTO> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password
				+ ", registeredOn=" + registeredOn + ", groups=" + groups + "]";
	}
}
