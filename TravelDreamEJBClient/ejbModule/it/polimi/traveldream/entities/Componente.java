package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**Componente di TravelDream*/

@Entity
public class Componente implements Serializable {

	/**Version number*/
	private static final long serialVersionUID = 104L;

	/**Attributi*/
	@Id
	@GeneratedValue
	private Long codiceComponente;
	private Tipologia tipologia;
	private String descrizione;

	/**Costruttore*/
	public Componente() {
		super();
	}

	/**@return codiceComponente*/
	public Long getCodiceComponente() {
		
		return this.codiceComponente;
		
	}

	/**@return tipologia*/
	public Tipologia getTipologia() {

		return this.tipologia;

	}
	
	/**@param tipologia tipologia to set*/
	public void setTipologia(Tipologia tipologia) {

		this.tipologia = tipologia;

	}

	/**@return descrizione*/
	public String getDescrizione() {
		
		return this.descrizione;
		
	}

	/**@param descrizione descrizione to set*/
	public void setDescrizione(String descrizione) {
		
		this.descrizione = descrizione;
		
	}
}
