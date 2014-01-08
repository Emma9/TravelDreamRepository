package it.polimi.traveldream.web.beans;

	import java.io.Serializable;  
	import java.util.ArrayList;  
	import java.util.List;  
	import java.util.UUID;  
	import it.polimi.traveldream.ejb.*;
	//import org.primefaces.examples.domain.Car;
public class TabellaPacchettiBean implements Serializable{ 
	 
	  
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private List<PacchettoBeanLocal> listaPacchettiLastMinute;

	    private List<PacchettoBeanLocal> listaPacchettiOfferte; 

	    private List<PacchettoBeanLocal> listaPacchettiMare; 

	    private List<PacchettoBeanLocal> listaPacchettiMontagna; 
	    
	    private PacchettoBeanLocal pacchettoLastMinuteSelezionato;
	    
	  
	    public TabellaPacchettiBean() {  
	    	listaPacchettiLastMinute = new ArrayList<PacchettoBeanLocal>();  
	    	listaPacchettiOfferte = new ArrayList<PacchettoBeanLocal>(); 
	    	listaPacchettiMare = new ArrayList<PacchettoBeanLocal>();  
	    	listaPacchettiMontagna = new ArrayList<PacchettoBeanLocal>(); 
	  
	        popolaPacchettiLastMinute(listaPacchettiLastMinute);  
	    }  
	  
	    private void popolaPacchettiLastMinute(List<PacchettoBeanLocal> listaPacchettiLastMinute) { 
	        	listaPacchettiLastMinute.add(new PacchettoBeanLocal());  
	    }  
	  
	    public List<PacchettoBeanLocal> getlistaPacchettiLastMinute() {  
	        return listaPacchettiLastMinute;  
	    }  
	    public List<PacchettoBeanLocal> getlistaPacchettiOfferte() {  
	        return listaPacchettiOfferte;  
	    } 
	    public List<PacchettoBeanLocal> getlistaPacchettiMare() {  
	        return listaPacchettiMare;  
	    } 
	    public List<PacchettoBeanLocal> getlistaPacchettiMontagna() {  
	        return listaPacchettiMontagna;  
	    } 
	    public PacchettoBeanLocal getPacchettoLastMinuteSelezionato() {  
	        return pacchettoLastMinuteSelezionato;  
	    }  
	  
	    public void setPacchettoLastMinuteSelezionato(PacchettoBeanLocal pacchettoLastMinuteSelezionato) {  
	        this.pacchettoLastMinuteSelezionato = pacchettoLastMinuteSelezionato;  
	    } 
	   
	} 
