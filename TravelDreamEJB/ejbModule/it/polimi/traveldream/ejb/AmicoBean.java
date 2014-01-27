package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.AmicoBeanLocal;
import it.polimi.traveldream.ejb.client.AmicoBeanRemote;
import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.Amico;
import it.polimi.traveldream.entities.AmicoDTO;

import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.ejb.Stateless;

/**Session Bean implementation class AmicoBean*/
@Stateless
public class AmicoBean implements AmicoBeanRemote, AmicoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") 
	private EntityManager manager;

	/**Default constructor*/
	public AmicoBean() {
		// TODO Auto-generated constructor stub
	}

	/**@param email
	 * @return idAmico*/
	public Long createAmico(String email) {

		if (!(verificaPresenzaAmicoEm(email))) {

			Amico amico = new Amico();

			amico.setEmail(email);

			manager.persist(amico);

			return amico.getIdAmico();

		} else {
			return (long) -1;
		}
	}

	/**@param idAmico */
	public void removeAmico(Long idAmico) {

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			InvitoBeanRemote invitoLocal = (InvitoBeanRemote) ctx.lookup("InvitoBean/local");

			invitoLocal.removeInvitiCliente(daIdAEmail(idAmico));

			AmicoDTO a = findByIdAmico(idAmico);
			
			manager.remove(a);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**@param idAmico
	 * @param email*/
	public void updateAmico(Long idAmico, String email) {

		if (verificaPresenzaAmicoId(idAmico)) {

			AmicoDTO amico = findByIdAmico(idAmico);

			amico.setEmail(email);

			manager.merge(amico);
		}
	}

	/**@param idAmico
	 * @return Amico*/
	public AmicoDTO findByIdAmico(Long idAmico) {

		TypedQuery<AmicoDTO> q = manager.createQuery("FROM Amico a WHERE a.idAmico=:new_idAmico", AmicoDTO.class);

		q.setParameter("new_idAmico", idAmico);

		AmicoDTO amico = q.getSingleResult();

		return amico;
	}

	/**@return ArrayList<idAmico>*/
	public ArrayList<Long> findAll() {

		TypedQuery<AmicoDTO> q = manager.createQuery("FROM Amico a", AmicoDTO.class);

		List<AmicoDTO> amici = q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= amici.size(); i++) {

			lista.set(i, amici.get(i).getIdAmico());
		}

		return lista;
	}

	/** Metodi private */

	/**@param email
	 * @return true if email is present in the DB, otherwise false*/
	public boolean verificaPresenzaAmicoEm(String email) {
		try {
			TypedQuery<AmicoDTO> q = manager
					.createQuery("FROM Amico a WHERE a.email=:new_email", AmicoDTO.class);

			q.setParameter("new_email", email);

			List<AmicoDTO> amici = q.getResultList();

			if (amici.size() == 0) {
				return false;

			} else {
				return true;

			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**@param idAmico
	 * @return true if idAmico is not present in the DB, otherwise false*/
	public boolean verificaPresenzaAmicoId(Long idAmico) {
		try {
			TypedQuery<AmicoDTO> q = manager.createQuery("FROM Amico a WHERE a.idAmico=:new_idAmico", AmicoDTO.class);

			q.setParameter("new_idAmico", idAmico);

			List<AmicoDTO> amici = q.getResultList();

			if (amici.size() == 0) {
				return true;

			} else {
				return false;

			}
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**@param idAmico
	 * @return email*/
	public String daIdAEmail(Long idAmico) {
		AmicoDTO a = findByIdAmico(idAmico);
		return a.getEmail();
	}
}
