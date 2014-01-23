package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.TipologiaDTO;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface ComponenteBeanRemote {

	/**@param tipologia
	 * @param descrizione
	 * @return codiceComponente*/
	public Long createComponente(TipologiaDTO tipologia, String descrizione);

	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente);

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione*/
	public void updateComponente(Long codiceComponente, TipologiaDTO tipologia,String descrizione);

	/**@param codiceComponente
	 * @return Componente*/
	public ComponenteDTO findByCodiceComponente(Long codiceCompoente);

	/**@return ArrayList<codiceComponente>*/
	public ArrayList<Long> findAll();

}