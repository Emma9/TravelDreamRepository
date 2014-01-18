package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.AmicoBeanLocal;
import it.polimi.traveldream.ejb.client.AmicoBeanRemote;
import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.Amico;

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

		if (verificaPresenzaAmicoEm(email)) {

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

			invitoLocal.removeInvito(daIdAEmail(idAmico));

			Amico a = findByIdAmico(idAmico);
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

			Amico amico = findByIdAmico(idAmico);

			amico.setEmail(email);

			manager.merge(amico);
		}
	}

	/**@param idAmico
	 * @return Amico*/
	public Amico findByIdAmico(Long idAmico) {

		Query q = manager.createQuery("FROM Amico a WHERE a.idAmico=:new_idAmico");

		q.setParameter("new_idAmico", idAmico);

		Amico amico = (Amico) q.getSingleResult();

		return amico;
	}

	/**@return ArrayList<idAmico>*/
	public ArrayList<Long> findAll() {

		Query q = manager.createQuery("FROM Amico a");

		@SuppressWarnings("unchecked")
		ArrayList<Amico> amici = (ArrayList<Amico>) q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= amici.size(); i++) {

			lista.set(i, amici.get(i).getIdAmico());
		}

		return lista;
	}

	/** Metodi private */

	/**@param email
	 * @return true if email is not present in the DB, otherwise false*/
	private boolean verificaPresenzaAmicoEm(String email) {
		try {
			Query q = manager
					.createQuery("FROM Amico a WHERE a.email=:new_email");

			q.setParameter("new_email", email);

			@SuppressWarnings("unchecked")
			ArrayList<Amico> amici = (ArrayList<Amico>) q.getResultList();

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
	 * @return true if idAmico is not present in the DB, otherwise false*/
	private boolean verificaPresenzaAmicoId(Long idAmico) {
		try {
			Query q = manager.createQuery("FROM Amico a WHERE a.idAmico=:new_idAmico");

			q.setParameter("new_idAmico", idAmico);

			@SuppressWarnings("unchecked")
			ArrayList<Amico> amici = (ArrayList<Amico>) q.getResultList();

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
	private String daIdAEmail(Long idAmico) {
		Amico a = findByIdAmico(idAmico);
		return a.getEmail();
	}
}
