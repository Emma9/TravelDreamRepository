package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.Componente;
import it.polimi.traveldream.entities.Tipologia;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface ComponenteBeanRemote {

	/**@param tipologia
	 * @param descrizione
	 * @return codiceComponente*/
	public Long createComponente(Tipologia tipologia, String descrizione);

	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente);

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione*/
	public void updateComponente(Long codiceComponente, Tipologia tipologia,String descrizione);

	/**@param codiceComponente
	 * @return Componente*/
	public Componente findByCodiceComponente(Long codiceCompoente);

	/**@return ArrayList<codiceComponente>*/
	public ArrayList<Long> findAll();

}