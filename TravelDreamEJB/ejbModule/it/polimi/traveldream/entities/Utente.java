package it.polimi.traveldream.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="utenti")
@NamedQuery(name="utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idUtente;
	

	private String password;

	//bi-directional many-to-many association to Group
	@ManyToMany
	@JoinTable(
		name="gruppo_utente"
		, joinColumns={
			@JoinColumn(name="idUtente", referencedColumnName="idUtente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idGruppo", referencedColumnName="idGruppo")
			}
		)
	private List<Gruppo> gruppi;

	public Utente() {
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Gruppo> getGruppi() {
		return this.gruppi;
	}

	public void setGroups(List<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
}