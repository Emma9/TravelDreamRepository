package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DisponibilitaPerDataPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codiceComponente;
	@Temporal(TemporalType.DATE)
	private Date data;
	
	public DisponibilitaPerDataPK() {}

    public DisponibilitaPerDataPK(int codiceComponente, Date data) {
        this.codiceComponente= codiceComponente;
        this.data = data;
    }

    public boolean equals(Object object) {
        if (object instanceof DisponibilitaPerDataPK) {
        	DisponibilitaPerDataPK pk = (DisponibilitaPerDataPK)object;
            return codiceComponente==pk.codiceComponente && data == pk.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return data.hashCode() + codiceComponente;
    }

}
