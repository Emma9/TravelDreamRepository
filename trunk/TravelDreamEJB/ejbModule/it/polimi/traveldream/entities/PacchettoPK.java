package it.polimi.traveldream.entities;

import java.io.Serializable;

public class PacchettoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idPacchetto;
	private Long idPacchettoPersonalizzato;

	public PacchettoPK() {
	}

	public PacchettoPK(Long idPacchetto, Long idPacchettoPersonalizzato) {

		this.idPacchetto = idPacchetto;
		this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;

	}

	public boolean equals(Object object) {

		if (object instanceof PacchettoPK) {

			PacchettoPK pk = (PacchettoPK) object;
			return idPacchetto == pk.idPacchetto
					&& idPacchettoPersonalizzato == pk.idPacchettoPersonalizzato;

		} else {
			return false;
		}

	}

	public int hashCode() {

		return (int) (idPacchetto + idPacchettoPersonalizzato);

	}

}
