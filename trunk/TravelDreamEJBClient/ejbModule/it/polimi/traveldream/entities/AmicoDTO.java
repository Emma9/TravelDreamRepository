package it.polimi.traveldream.entities;

import java.io.Serializable;

public class AmicoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 207L;
	
	private Long idAmico;
	private String email;

	/**Costruttore*/
	public AmicoDTO() {
		super();
	}

	/**@return idAmico*/
	public Long getIdAmico() {
		
		return this.idAmico;
		
	}
	
	/**
	 * @param idAmico the idAmico to set
	 */
	public void setIdAmico(Long idAmico) {
		this.idAmico = idAmico;
	}

	/**@return email*/
	public String getEmail() {
		
		return this.email;
		
	}

	/**@param email email to set*/
	public void setEmail(String email) {
		
		this.email = email;
		
	}

	
	
	
}
