package it.polimi.traveldream.entities;





import javax.persistence.*;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="utenti")
@NamedQuery(name="utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 100L;
	@Id
	private String email;
	
    private String firstName;
      
    private String lastName;
	
	private String password; //sha-512 + hex
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registeredOn;
	
	//bi-directional many-to-many association to Group
		@ManyToMany
		@JoinTable(
			name="USER_GROUP"
			, joinColumns={
				@JoinColumn(name="USERID", referencedColumnName="USERID")
				}
			, inverseJoinColumns={
				@JoinColumn(name="GROUPID", referencedColumnName="GROUPID")
				}
			)
		private List<Gruppo> groups;

	public Utente() {
		super();
	}
	
	public Utente(UtenteDTO utente){
         
        this.email        = utente.getEmail();
        this.firstName    = utente.getFirstName();
        this.lastName     = utente.getLastName();        
        this.password     = DigestUtils.sha512Hex(utente.getPassword() );
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
 
    public List<Gruppo> getGroups() {
        return groups;
    }
 
    public void setGroups(List<Gruppo> groups) {
        this.groups = groups;
    }
 
    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", password=" + password
                + ", registeredOn=" + registeredOn + ", gruppi=" + groups + "]";
    }
}
