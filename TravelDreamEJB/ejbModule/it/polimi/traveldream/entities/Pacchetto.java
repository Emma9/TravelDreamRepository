package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Pacchetto di TravelDream
 *
 */

@Entity

public class Pacchetto implements Serializable {

	/**Version number */
	private static final long serialVersionUID = 108L;
	   
	/**Attributi */
	@Id
	private Long idPacchetto;
	private String destinazione;
	private String etichetta;
	private String descrizione;
	
	/**Costruttore */
	public Pacchetto() {
		super();
	}   
	
	/**@return idPacchetto*/
	public Long getIdPacchetto() {
		return this.idPacchetto;
	}

	/**@param idPacchetto idPacchetto to set*/
	public void setIdPacchetto(Long idPacchetto) {
		this.idPacchetto = idPacchetto;
	}
	
	/**@return destinazione*/
	public String getDestinazione() {
		return this.destinazione;
	}

	/**@param destinazione destinazione to set*/
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	} 
	
	/**@return etichetta*/
	public String getEtichetta() {
		return this.etichetta;
	}

	/**@param etichetta etichetta to set*/
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
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