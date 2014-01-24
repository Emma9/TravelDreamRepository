package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean()
@SessionScoped
public class RicercaPerEtichettaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PacchettoBeanRemote pacchettoRemoto;
	
//	private Etichetta etichetta;
	private Date dataPartenza;
	private Date dataArrivo;
	private int numPartecipanti;

}
