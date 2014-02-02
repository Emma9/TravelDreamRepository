package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DisponibilitaPerDataPKDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codiceComponente;
	@Temporal(TemporalType.DATE)
	private Date data;
	
	public DisponibilitaPerDataPKDTO() {}

    public DisponibilitaPerDataPKDTO(int codiceComponente, Date data) {
        this.codiceComponente= codiceComponente;
        this.data = data;
    }

    public boolean equals(Object object) {
        if (object instanceof DisponibilitaPerDataPKDTO) {
        	DisponibilitaPerDataPKDTO pk = (DisponibilitaPerDataPKDTO)object;
            return codiceComponente==pk.codiceComponente && data == pk.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return data.hashCode() + codiceComponente;
    }

}
