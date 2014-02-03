package it.polimi.traveldream.entities;



import it.polimi.traveldream.entities.UserDTO;

import java.io.Serializable;
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
@Table(name="USERS")
@NamedQueries({
	@NamedQuery(name=User.FIND_ALL,
				query="SELECT u FROM User u ORDER BY u.registeredOn ASC")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "User.findAll";
	   
	@Id
	private String username;
	
    private String firstName;
      
    private String lastName;
	
	private String password; //sha-512 + hex
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registeredOn;
	
	@ElementCollection(targetClass = Group.class)
    @CollectionTable(name = "USERS_GROUPS",
                    joinColumns = @JoinColumn(name = "username"))
    @Enumerated(EnumType.STRING)
    @Column(name="groupname")
    private List<Group> groups;

	public User() {
		super();
	}
	
	public User(UserDTO user){
         
        this.username     = user.getUsername();
        this.firstName    = user.getFirstName();
        this.lastName     = user.getLastName();        
        this.password     = DigestUtils.sha256Hex(user.getPassword());
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
 
    public List<Group> getGroups() {
        return groups;
    }
 
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
 
    @Override
    public String toString() {
        return "User [username=" + username + ", firstName=" + firstName
                + ", lastName=" + lastName + ", password=" + password
                + ", registeredOn=" + registeredOn + ", groups=" + groups + "]";
    }
}
