package it.polimi.traveldream.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

import org.apache.commons.codec.digest.DigestUtils;

public class UtenteDTO {

private String email;
	
    private String firstName;
      
    private String lastName;
	
	private String password; //sha-512 + hex
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registeredOn;
	
	
	private List<GruppoDTO> groups;

	public UtenteDTO() {
		super();
	}
	
	public UtenteDTO(UtenteDTO utente){
         
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
 
    public List<GruppoDTO> getGroups() {
        return groups;
    }
 
    public void setGroups(List<GruppoDTO> groups) {
        this.groups = groups;
    }
 
 
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", password=" + password
                + ", registeredOn=" + registeredOn + ", gruppi=" + groups + "]";
    }
}

	
	
	

