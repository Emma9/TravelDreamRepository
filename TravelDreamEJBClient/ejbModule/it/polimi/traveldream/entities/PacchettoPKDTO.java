package it.polimi.traveldream.entities;

import java.io.Serializable;

public class PacchettoPKDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idPacchetto;
	private Long idPacchettoPersonalizzato;

	public PacchettoPKDTO() {
	}

	public PacchettoPKDTO(Long idPacchetto, Long idPacchettoPersonalizzato) {

		this.idPacchetto = idPacchetto;
		this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;

	}

	public boolean equals(Object object) {

		if (object instanceof PacchettoPKDTO) {

			PacchettoPKDTO pk = (PacchettoPKDTO) object;
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
