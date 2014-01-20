package it.polimi.traveldream.web.beans;

public interface Registrazione {

	void inserisciNelDatabase(String nome, String cognome,
			String codiceFiscale, String email, String password);

}
