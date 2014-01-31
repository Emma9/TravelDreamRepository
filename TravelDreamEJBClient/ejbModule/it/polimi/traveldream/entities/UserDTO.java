package it.polimi.traveldream.entities;



import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDTO {
	public static final String FIND_ALL = "User.findAll";
	   
	@Id
	private String email;
	
    private String firstName;
      
    private String lastName;
	
	private String password; //sha-512 + hex
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registeredOn;
	
	@ElementCollection(targetClass = GroupDTO.class)
    @CollectionTable(name = "USERS_GROUPS",
                    joinColumns = @JoinColumn(name = "email"))
    @Enumerated(EnumType.STRING)
    @Column(name="groupname")
    private List<GroupDTO> groups;

	public UserDTO() {
		super();
	}
	
	public UserDTO(UserDTO user){
         
        this.email        = user.getEmail();
        this.firstName    = user.getFirstName();
        this.lastName     = user.getLastName();        
        this.password     = DigestUtils.sha512Hex(user.getPassword() );
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
 
    public String getEmail() {
        return email;
    }
  
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
