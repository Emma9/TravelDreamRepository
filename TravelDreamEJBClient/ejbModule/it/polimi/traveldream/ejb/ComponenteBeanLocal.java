package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface ComponenteBeanLocal {
	
	/**@param tipologia
	 * @param descrizione
	 * @return codiceComponente*/
	public Long createComponente(String tipologia,String descrizione);
	
	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente);

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione*/
	public void updateComponente(Long codiceComponente,String tipologia,String descrizione);

	/**@return ArrayList<codiceComponente>*/
	public ArrayList<Long> findAll();

}
