package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface PacchettoBeanLocal {
	
	/**@param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti
	 * @return idPacchetto*/
	public Long createPacchetto(String destinazione,String etichetta,String descrizione,ArrayList<Long> listaComponenti);
	
	/**@param idPacchetto*/
	public void removePacchetto(Long idPacchetto);

	/**@param idPacchetto
	 * @param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchetto(Long idPacchetto,String destinazione,String etichetta,String descrzione,ArrayList<Long> listaComponenti);
	
	/**@param destinazione
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByDestinazione(String destinazione);
	
	/**@param etichetta
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByEtichetta(String etichetta);	

	/**@return ArrayList<idPacchetto>*/
	public ArrayList<Long> findAll();

}
