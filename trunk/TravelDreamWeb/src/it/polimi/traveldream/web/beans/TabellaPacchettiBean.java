package it.polimi.traveldream.web.beans;

	import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
	//import org.primefaces.examples.domain.Car;
public class TabellaPacchettiBean implements Serializable{ 
	 
	  
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private List<PacchettoBeanRemote> listaPacchettiLastMinute;

	    private List<PacchettoBeanRemote> listaPacchettiOfferte; 

	    private List<PacchettoBeanRemote> listaPacchettiMare; 

	    private List<PacchettoBeanRemote> listaPacchettiMontagna; 
	    
	    private PacchettoBeanRemote pacchettoLastMinuteSelezionato;
	    
	  
	    public TabellaPacchettiBean() {  
	    	listaPacchettiLastMinute = new ArrayList<PacchettoBeanRemote>();  
	    	listaPacchettiOfferte = new ArrayList<PacchettoBeanRemote>(); 
	    	listaPacchettiMare = new ArrayList<PacchettoBeanRemote>();  
	    	listaPacchettiMontagna = new ArrayList<PacchettoBeanRemote>(); 
	  
	        popolaPacchettiLastMinute(listaPacchettiLastMinute);  
	    }  
	  
	    
	    // pacchetto bean remote è un interfaccia non una classe1
	    private void popolaPacchettiLastMinute(List<PacchettoBeanRemote> listaPacchettiLastMinute) { 
	     //   	listaPacchettiLastMinute.add(new PacchettoBeanRemote());  
	    }  
	  
	    public List<PacchettoBeanRemote> getlistaPacchettiLastMinute() {  
	        return listaPacchettiLastMinute;  
	    }  
	    public List<PacchettoBeanRemote> getlistaPacchettiOfferte() {  
	        return listaPacchettiOfferte;  
	    } 
	    public List<PacchettoBeanRemote> getlistaPacchettiMare() {  
	        return listaPacchettiMare;  
	    } 
	    public List<PacchettoBeanRemote> getlistaPacchettiMontagna() {  
	        return listaPacchettiMontagna;  
	    } 
	    public PacchettoBeanRemote getPacchettoLastMinuteSelezionato() {  
	        return pacchettoLastMinuteSelezionato;  
	    }  
	  
	    public void setPacchettoLastMinuteSelezionato(PacchettoBeanRemote pacchettoLastMinuteSelezionato) {  
	        this.pacchettoLastMinuteSelezionato = pacchettoLastMinuteSelezionato;  
	    } 
	   
	} 
