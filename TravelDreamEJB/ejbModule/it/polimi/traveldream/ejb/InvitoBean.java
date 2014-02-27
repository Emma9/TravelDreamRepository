package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.InvitoBeanLocal;
import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.Invito;
import it.polimi.traveldream.entities.InvitoDTO;
import it.polimi.traveldream.entities.User;
import it.polimi.traveldream.entities.UserDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/** Session Bean implementation class InvitoBean */
@Stateless
public class InvitoBean implements InvitoBeanRemote, InvitoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project")
	private EntityManager manager;

	/** Default constructor */
	public InvitoBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato
	 * @return idInvito
	 */
	public Long createInvito(String mittente, String destinatario,
			Long idpacchettoPersonalizzato, Date data, boolean stato) {

		System.out.println("CREATEINVITO --> METODO "+destinatario);

		Invito invito = new Invito();

		// UsrMgrBean usrmgrbean = new UsrMgrBean();

		System.out.println("CREATEINVITO --> NUOVO INVITO");

		invito.setEmailMittente(mittente);

		System.out.println("CREATEINVITO --> SETEMAILMITTENTE");

		//AmicoBean amicobean = new AmicoBean();

		//System.out.println("CREATEINVITO --> NUOVO AMICO");

		//amicobean.createAmico(destinatario);

		System.out.println("CREATEINVITO --> CREATE AMICO");

		invito.setEmailDestinatario(destinatario);

		System.out.println("CREATEINVITO --> SETEMAILDESTINATARIO");

		invito.setIdPacchettoPersonalizzato(idpacchettoPersonalizzato);

		System.out.println("CREATEINVITO --> SETIDPP");

		invito.setData(data);

		System.out.println("CREATEINVITO --> SETDATA");

		invito.setStato(stato);

		System.out.println("CREATEINVITO --> SETSTATO");

		manager.persist(invito);

		System.out.println("CREATEINVITO --> PERSIST");

		return invito.getIdInvito();
	}

	/**
	 * @param idInvito
	 */
	public void removeInvito(Long idInvito) {

		// InvitoDTO invito= findByIdInvito(idInvito);

		Invito invito = manager.find(Invito.class, idInvito);

		manager.remove(invito);
	}

	/** @param emailMittente */
	public void removeInvitiCliente(String emailMittente) {

		List<InvitoDTO> inviti = new ArrayList<InvitoDTO>();

		inviti = findByEmailMittente(emailMittente);

		for (int i = 0; i < inviti.size(); i++) {

			manager.remove(inviti.get(i));
		}
	}

	/**
	 * @param idInvito
	 * @param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato
	 */
	public void updateInvito(Long idInvito, String mittente,
			String destinatario, Long idpacchettoPersonalizzato, Date data,
			boolean stato) {

		if (verificaPresenzaInvito(idInvito)) {

			// InvitoDTO invito = findByIdInvito(idInvito);

			Invito invito = manager.find(Invito.class, idInvito);
			// UsrMgrBean usrmgrbean = new UsrMgrBean();
			invito.setEmailMittente(mittente);

			//AmicoBean amicobean = new AmicoBean();

			//amicobean.updateAmico(destinatario);

			invito.setEmailDestinatario(destinatario);

			invito.setIdPacchettoPersonalizzato(idpacchettoPersonalizzato);
			invito.setData(data);
			
			invito.setStato(stato);

			manager.merge(invito);
		}

	}

	/**
	 * @param idInvito
	 * @return InvitoDTO
	 */
	public InvitoDTO findByIdInvito(Long idInvito) {

		TypedQuery<Invito> q = manager.createQuery(
				"FROM Invito i WHERE i.idInvito=:new_idInvito", Invito.class);

		q.setParameter("new_idInvito", idInvito);

		InvitoDTO invito = invitoToDTO(q.getSingleResult());

		return invito;
	}

	/**
	 * @param emailMittente
	 * @return ArrayList<InvitoDTO>
	 */
	public ArrayList<InvitoDTO> findByEmailMittente(String emailMittente) {

		TypedQuery<Invito> q = manager.createQuery(
				"FROM Invito i WHERE i.emailMittente=:new_emailMittente",
				Invito.class);

		q.setParameter("new_emailMittente", emailMittente);

		ArrayList<InvitoDTO> inviti = new ArrayList<InvitoDTO>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			inviti.add(invitoToDTO(q.getResultList().get(i)));

		}
		return inviti;
	}

	/**
	 * @param mittente
	 * @return ArrayList<InvitoDTO>
	 */
	public ArrayList<InvitoDTO> findByMittente(UserDTO mittente) {

		UsrMgrBean usrmgrbean = new UsrMgrBean();
		User mit = usrmgrbean.userDTOToUser(mittente);

		TypedQuery<Invito> q = manager.createQuery(
				"FROM Invito i WHERE i.mittente=:new_mittente", Invito.class);

		q.setParameter("new_mittente", mit);

		ArrayList<InvitoDTO> inviti = new ArrayList<InvitoDTO>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			inviti.add(invitoToDTO(q.getResultList().get(i)));

		}
		return inviti;
	}

	/** @return ArrayList<InvitoDTO> */
	public ArrayList<InvitoDTO> findAll() {

		TypedQuery<Invito> q = manager.createQuery("FROM Invito i",
				Invito.class);

		ArrayList<InvitoDTO> lista = new ArrayList<InvitoDTO>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			lista.add(invitoToDTO(q.getResultList().get(i)));

		}
		return lista;
	}

	/**
	 * @param idInvito
	 * @return true if idInvito is present in the DB, otherwise false
	 */
	public boolean verificaPresenzaInvito(Long idInvito) {
		try {
			TypedQuery<Invito> q = manager.createQuery(
					"FROM Invito i WHERE i.idInvito=:new_idInvito",
					Invito.class);

			q.setParameter("new_idInvito", idInvito);

			if (q.getResultList().isEmpty()) {

				return false;

			}

			return true;

		} catch (NullPointerException e) {

			return false;

		}
	}

	public InvitoDTO invitoToDTO(Invito invito) {

		InvitoDTO invitoDTO = new InvitoDTO();

		invitoDTO.setIdInvito(invito.getIdInvito());
		// UsrMgrBean usrmgrbean = new UsrMgrBean();
		invitoDTO.setEmailMittente(invito.getEmailMittente());
		invitoDTO.setEmailDestinatario(invito.getEmailDestinatario());
		invitoDTO.setData(invito.getData());
		invitoDTO.setIdPacchettoPersonalizzato(invito
				.getIdPacchettoPersonalizzato());
		invitoDTO.setStato(invito.getStato());

		return invitoDTO;
	}

}