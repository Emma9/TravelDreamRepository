package it.polimi.traveldream.entities;



import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GROUPS database table.
 * 
 */
@Entity
@Table(name="gruppi")
@NamedQuery(name="Gruppo.findAll", query="SELECT g FROM Gruppo g")
public class Gruppo implements Serializable {
	private static final long serialVersionUID = 101L;
	
	@Id
	private String idGruppo;

	//bi-directional many-to-many association to Utente
	@ManyToMany(mappedBy="gruppi")
	private List<Utente> utenti;

	public Gruppo() {
	}

	public List<Utente> getUtenti() {
		return this.utenti;
	}

	public void setUsers(List<Utente> utenti) {
		this.utenti = utenti;
	}
	
	public String getGroupId() {
		return this.idGruppo;
	}

	public void setGroupId(String idGruppo) {
		this.idGruppo = idGruppo;
	}

}

