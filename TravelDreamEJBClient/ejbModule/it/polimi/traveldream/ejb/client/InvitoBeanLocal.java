package it.polimi.traveldream.ejb.client;

import javax.ejb.Local;

@Local
public interface InvitoBeanLocal {

	public void eliminaTuttiInviti(long idCliente);
	
}