package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PacchettoBeanRemote {

	/**@param destinazione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti
	 * @param sconto
	 * @return idPacchetto*/
	public Long createPacchetto(String destinazione, Date dataInizioValidita, Date dataFineValidita, String etichetta, String descrizione, List<ComponenteDTO> listaComponenti, int sconto);

	/**@param idPacchetto*/
	public void removePacchetto(Long idPacchetto);

	/**@param idPacchetto
	 * @param destinazione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti
	 * @param sconto
	 */
	public void updatePacchetto(Long idPacchetto, String destinazione, Date dataInizioValidita, Date dataFineValidita, String etichetta, String descrizione, List<ComponenteDTO> listaComponenti, int sconto);

	/**@param destinazione
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByDestinazione(String destinazione);

	/**@param etichetta
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByEtichetta(String etichetta);

	/**@param idPacchetto
	 * @return Pacchetto*/
	public PacchettoDTO findByIdPacchetto(Long idPacchetto);

	/** @return ArrayList<idPacchetto> */
	public ArrayList<Long> findAll();
	
	/**@param dataPartenza
	 * @param dataRitorno
	 * @return true if dataPartenza comes before dataRitorno, otherwise false*/
	public boolean verificaConsistenzaDate (Date dataPartenza, Date dataRitorno);
	
	/**@param dataPartenza
	 * @param dataRitorno
	 * @param disponibilita
	 * @param listaComponenti
	 * @return true if , otherwise false*/
	public boolean verificaDisponibilitaComponenti (Date dataPartenza, Date dataRitorno, int disponibilita, List<ComponenteDTO> listaComponenti);
	/**@param idPacchetto
	 * @return true if idPacchetto is not present in the DB, otherwise false*/
	public boolean verificaPresenzaPacchetto(Long idPacchetto);
	
	/**@param listaComponenti
	 * @return true if listaComponenti contains more than two elements
	 *  and at least three of them are of a different type, otherwise false
	 */
	public boolean verificaListaComponenti (List<ComponenteDTO> listaComponenti);
	
	
	public String[] splitEtichetta(String etichetta);
	
	/**@param etichetta
	 * @return true if etichetta is valid, otherwise false	
	 */
	public boolean verificaEtichetta (String etichetta);

	/**@param destinazione
	 * @param dataPartenza
	 * @param dataRitorno
	 * @return ArrayList<Pacchetto>
	 */
	public ArrayList<PacchettoDTO> ricercaPacchetti (String destinazione, Date dataPartenza, Date dataRitorno);
	
	/**@param etichetta
	 * @param dataPartenza
	 * @param dataRitorno
	 * @return ArrayList<Pacchetto>
	 */
	public ArrayList<PacchettoDTO> ricercaPerEtichetta (String etichetta, Date dataPartenza, Date dataRitorno);
	
	public boolean verificaComponentiSelezionatiInComponenti(List<ComponenteDTO> listaComponenti, List<ComponenteDTO> listaComponentiSelezionati);
	
	public boolean verificaTreComponentiSelezionati(List<ComponenteDTO> listaComponentiSelezionati);
	
	public List<ComponenteDTO> modificaListaComponentiSelezionati (List<ComponenteDTO> listaComponentiSelezionati, ComponenteDTO componenteDaInserire);
	
	public int calcolaCostoPacchetto (List<ComponenteDTO> listaComponenti, int sconto);
	
	
	
}
