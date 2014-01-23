package it.polimi.traveldream.entities;

import java.io.Serializable;

public class ComponenteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codiceComponente;
	private TipologiaDTO tipologia;
	private String descrizione;

	/**Costruttore*/
	public ComponenteDTO() {
		super();
	}

	/**@return codiceComponente*/
	public Long getCodiceComponente() {
		
		return this.codiceComponente;
		
	}

	/**@return tipologia*/
	public TipologiaDTO getTipologia() {

		return this.tipologia;

	}
	
	/**@param tipologia tipologia to set*/
	public void setTipologia(TipologiaDTO tipologia) {

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
