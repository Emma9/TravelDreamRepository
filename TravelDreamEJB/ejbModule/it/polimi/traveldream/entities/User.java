package it.polimi.traveldream.entities;

import it.polimi.traveldream.entities.UserDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Entity implementation class for Entity: UserEntity
 * 
 */
@Entity
@Table(name = "USERS")
@NamedQueries({ @NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u ORDER BY u.registeredOn ASC") })
public class User implements Serializable {
	
	
	private static final long serialVersionUID = 109872L;

	public static final String FIND_ALL = "User.findAll";

	@Id
	private String email;

	private String firstName;

	private String lastName;

	private String password; // sha-512 + hex
	
	
	private List<PacchettoPersonalizzato> pacchettiCliente = new ArrayList<PacchettoPersonalizzato>(0);
	
	private List<PacchettoPersonalizzato> giftList = new ArrayList<PacchettoPersonalizzato>(0);

	
	

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date registeredOn;

	@ElementCollection(targetClass = Group.class)
	@CollectionTable(name = "USERS_GROUPS", joinColumns = @JoinColumn(name = "email"))
	@Enumerated(EnumType.STRING)
	@Column(name = "groupname")
	private List<Group> groups;

	public User() {
		super();
	}

	public User(UserDTO user) {

		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = DigestUtils.sha256Hex(user.getPassword());
		this.registeredOn = new Date();
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
	public List<PacchettoPersonalizzato> getPacchettiCliente() {
		return pacchettiCliente;
	}

	/**
	 * @param pacchettiCliente the pacchettiCliente to set
	 */
	public void setPacchettiCliente(List<PacchettoPersonalizzato> pacchettiCliente) {
		this.pacchettiCliente = pacchettiCliente;
	}

	/**
	 * @return the giftList
	 */
	public List<PacchettoPersonalizzato> getGiftList() {
		return giftList;
	}

	/**
	 * @param giftList the giftList to set
	 */
	public void setGiftList(List<PacchettoPersonalizzato> giftList) {
		this.giftList = giftList;
	}

	public Date getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password
				+ ", registeredOn=" + registeredOn + ", groups=" + groups + "]";
	}
}
