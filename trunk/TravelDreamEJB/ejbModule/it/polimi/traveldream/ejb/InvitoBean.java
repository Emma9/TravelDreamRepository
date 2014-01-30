package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.InvitoBeanLocal;
import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.Invito;
import it.polimi.traveldream.entities.InvitoDTO;




import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**Session Bean implementation class InvitoBean*/
@Stateless
public class InvitoBean implements InvitoBeanRemote, InvitoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") private EntityManager manager;

	/**Default constructor*/
	public InvitoBean() {
		// TODO Auto-generated constructor stub
	}

	/**@param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato
	 * @return idInvito*/
	public Long createInvito(String emailMittente, String emailDestinatario,String idPacchettoPersonalizzato, String data, boolean stato) {

		Invito invito = new Invito();

		invito.setEmailMittente(emailMittente);
		invito.setEmailDestinatario(emailDestinatario);
		invito.setIdPacchettoPersonalizzato(idPacchettoPersonalizzato);
		invito.setData(data);
		invito.setStato(stato);

		manager.persist(invito);
		
		return invito.getIdInvito();
	}

	/**@param idInvito
	 */
	public void removeInvito(Long idInvito) {
		
		//InvitoDTO invito= findByIdInvito(idInvito);
		
		Invito invito = manager.find(Invito.class, idInvito);
		
		manager.remove(invito);
	}
	
	/**@param emailMittente*/
	public void removeInvitiCliente(String emailMittente) {

		//ArrayList<InvitoDTO> inviti = findByEmailMittente(emailMittente);

		ArrayList<Invito> inviti = findByEmailMittenteENT(emailMittente);
		
		for (int i = 0; i < inviti.size(); i++) {

			manager.remove(inviti.get(i));
		}
	}

	/**@param idInvito
	 * @param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato*/
	public void updateInvito(Long idInvito, String emailMittente,String emailDestinatario, String idPacchettoPersonalizzato,String data, boolean stato) {

		if (verificaPresenzaInvito(idInvito)) {

			//InvitoDTO invito = findByIdInvito(idInvito);

			Invito invito = manager.find(Invito.class, idInvito);
			
			invito.setEmailMittente(emailMittente);
			invito.setEmailDestinatario(emailDestinatario);
			invito.setIdPacchettoPersonalizzato(idPacchettoPersonalizzato);
			invito.setData(data);

			manager.merge(invito);
		}

	}

	/**@param idInvito
	 * @return InvitoDTO*/
	public InvitoDTO findByIdInvito(Long idInvito) {

		TypedQuery<InvitoDTO> q = manager.createQuery("FROM Invito i WHERE i.idInvito=:new_idInvito", InvitoDTO.class);

		q.setParameter("new_idInvito", idInvito);

		InvitoDTO invito = q.getSingleResult();

		return invito;
	}

	/**@param emailMittente
	 * @return ArrayList<InvitoDTO>*/
	public ArrayList<InvitoDTO> findByEmailMittente(String emailMittente) {

		TypedQuery<InvitoDTO> q = manager.createQuery("FROM Invito i WHERE i.emailMittente=:new_emailMittente", InvitoDTO.class);

		q.setParameter("new_emailMittente", emailMittente);

		ArrayList<InvitoDTO> inviti = new ArrayList<InvitoDTO>();
		List<InvitoDTO> risultati = q.getResultList();

		for (int i = 0; i < risultati.size(); i++) {

			inviti.set(i, risultati.get(i));
			
		}
		return inviti;
	}
	
	/**@param emailMittente
	 * @return ArrayList<Invito>*/
	public ArrayList<Invito> findByEmailMittenteENT(String emailMittente) {

		TypedQuery<Invito> q = manager.createQuery("FROM Invito i WHERE i.emailMittente=:new_emailMittente", Invito.class);

		q.setParameter("new_emailMittente", emailMittente);

		ArrayList<Invito> inviti = new ArrayList<Invito>();
		List<Invito> risultati = q.getResultList();

		for (int i = 0; i < risultati.size(); i++) {

			inviti.set(i, risultati.get(i));
			
		}
		return inviti;
	}
	

	/**@return ArrayList<InvitoDTO>*/
	public ArrayList<InvitoDTO> findAll() {
		
		TypedQuery<InvitoDTO> q = manager.createQuery("FROM Invito i", InvitoDTO.class);

		List<InvitoDTO> inviti = q.getResultList();

		ArrayList<InvitoDTO> lista = new ArrayList<InvitoDTO>();

		for (int i = 0; i < inviti.size(); i++) {

			lista.set(i, inviti.get(i));
			
		}
		return lista;
	}


	/**@param idInvito
	 * @return true if idInvito is present in the DB, otherwise false*/
	public boolean verificaPresenzaInvito(Long idInvito) {
		try {
			TypedQuery<InvitoDTO> q = manager.createQuery("FROM Invito i WHERE i.idInvito=:new_idInvito", InvitoDTO.class);

			q.setParameter("new_idInvito", idInvito);

			List<InvitoDTO> inviti = q.getResultList();

			if (inviti.size() == 0) {
				
				return false;

			} else {
				
				return true;

			}
		} catch (NullPointerException e) {
			
			return false;
			
		}
	}
}