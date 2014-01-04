package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class ComponenteBean
 */
@Stateless
public class ComponenteBean implements ComponenteBeanLocal {

    /**
     * Default constructor. 
     */
    public ComponenteBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param tipologia
	 * @param descrizione
	 * @return codiceComponente*/
	public Long createComponente(String tipologia,String descrizione) {
		return null;
	}
	
	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente) {
	}

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione*/
	public void updateComponente(Long codiceComponente,String tipologia,String descrizione) {
	}

	/**@return ArrayList<codiceComponente>*/
	public ArrayList<Long> findAll() {
		return null;
	}







}
