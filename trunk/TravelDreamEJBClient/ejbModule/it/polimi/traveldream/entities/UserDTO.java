package it.polimi.traveldream.entities;



import java.io.Serializable;
import java.util.Date;
import java.util.List;





import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "User.findAll";
	   
private String username;
	
    private String firstName;
      
    private String lastName;
	
	private String password; //sha-256 + hex
	
    private Date registeredOn;
	
	
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
        return "User [username=" + username + ", firstName=" + firstName
                + ", lastName=" + lastName + ", password=" + password
                + ", registeredOn=" + registeredOn + ", groups=" + groups + "]";
    }
}
