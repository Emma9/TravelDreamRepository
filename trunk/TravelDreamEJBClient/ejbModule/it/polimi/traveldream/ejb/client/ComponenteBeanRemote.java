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
	 * @return codiceComponente*/
	public Long createComponente(String tipologia, String descrizione, Date dataInizioValidita, Date dataFineValidita, int disponibilitaDaSettare);
	
	
	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente);

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 */
	public void updateComponente(Long codiceComponente, String tipologia, String descrizione, Date dataInizioValidita, Date dataFineValidita, int disponibilitaDaSettare);
	/**@param codiceComponente
	 * @return ComponenteDTO*/
	public ComponenteDTO findByCodiceComponente(Long codiceComponente);
	
	/**@return ArrayList<codiceComponente>*/
	public ArrayList<Long> findAll();
	
	/**@param dataPartenza
	 * @param dataRitorno
	 * @param componenteDTO
	 * @return true if componente is valid, otherwise false
	 */
	public boolean verificaValiditaComponente (Date dataPartenza, Date dataRitorno, ComponenteDTO componenteDTO);
	
	
	
	public int disponibilitaInData (ComponenteDTO componente, Date data);
	
	
	
	public boolean verificaDisponibilitaComponenteInUnaData (int disponibilitaRichiesta, Date data, ComponenteDTO componente);
	
	
	public boolean verificaDisponibilitaComponenteInPeriodo (int disponibilitaRichiesta, Date dataPartenza, Date dataRitorno, ComponenteDTO componente);
	
	
	/**@param codiceComponente
	 * @return true if codiceComponente is not present in the DB, otherwise false*/
	public boolean verificaPresenzaComponente(Long codiceComponente); 
	
	public boolean verificaTipologia (String tipologia);
	
	

}