package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.Etichetta;
import it.polimi.traveldream.entities.Pacchetto;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface PacchettoBeanRemote {

	/**@param destinazione
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti
	 * @return idPacchetto*/
	public Long createPacchetto(String destinazione, Date dataPartenza, Date dataRitorno, int disponibilita, ArrayList<Etichetta> etichette, String descrizione,ArrayList<Long> listaComponenti);

	/**@param idPacchetto*/
	public void removePacchetto(Long idPacchetto);

	/**@param idPacchetto
	 * @param destinazione
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchetto(Long idPacchetto, String destinazione, Date dataPartenza, Date dataRitorno, int disponibilita, ArrayList<Etichetta> etichette, String descrizione,ArrayList<Long> listaComponenti);

	/**@param destinazione
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByDestinazione(String destinazione);

	/**@param etichetta
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByEtichetta(Etichetta etichette);

	/**@param idPacchetto
	 * @return Pacchetto*/
	public Pacchetto findByIdPacchetto(Long idPacchetto);

	/**@return ArrayList<idPacchetto>*/
	public ArrayList<Long> findAll();
	
	/**@param dataPartenza
	 * @param dataRitorno
	 * @return true if dataPartenza comes before dataRitorno, otherwise false*/
	public boolean verificaConsistenzaDate (Date dataPartenza, Date dataRitorno);

}
