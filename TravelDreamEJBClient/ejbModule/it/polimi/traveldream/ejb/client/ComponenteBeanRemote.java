package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.ComponenteDTO;



import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface ComponenteBeanRemote {

	/**@param tipologia
	 * @param descrizione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param disponibilita
	 * @return codiceComponente*/
	public Long createComponente(String tipologia, String descrizione, Date dataInizioValidita, Date dataFineValidita, int disponibilita);
	
	
	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente);

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param disponibilita
	 */
	public void updateComponente(Long codiceComponente, String tipologia, String descrizione, Date dataInizioValidita, Date dataFineValidita, int disponibilita);
	/**@param codiceComponente
	 * @return Componente*/
	public ComponenteDTO findByCodiceComponente(Long codiceCompoente);

	/**@return ArrayList<codiceComponente>*/
	public ArrayList<Long> findAll();
	
	/**@param dataPartenza
	 * @param dataRitorno
	 * @param codiceComponente
	 * @return true if componente is valid, otherwise false
	 */
	public boolean verificaValiditaComponente (Date dataPartenza, Date dataRitorno, Long codiceComponente);
	
	/**@param disponibilita
	 * @param codiceComponente
	 * @return true if componente is available, otherwise false	
	 */
	public boolean verificaDisponibilitaComponente (int disponibilita, Long codiceComponente);

	/**@param codiceComponente
	 * @return true if codiceComponente is not present in the DB, otherwise false*/
	public boolean verificaPresenzaComponente(Long codiceComponente); 
	
	public boolean verificaTipologia (String tipologia);
	
	

}