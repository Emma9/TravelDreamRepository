package it.polimi.traveldream.ejb;

import javax.ejb.Local;

@Local
public interface PacchettoPersonalizzatoBeanLocal {

	public void eliminaTuttiPacchettiPersonalizzati(long idCliente);
	
}
