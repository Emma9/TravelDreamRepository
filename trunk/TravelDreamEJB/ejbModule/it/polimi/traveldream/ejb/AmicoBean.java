package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.AmicoBeanLocal;
import it.polimi.traveldream.ejb.client.AmicoBeanRemote;
import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.Amico;
import it.polimi.traveldream.entities.AmicoDTO;
import it.polimi.traveldream.entities.User;
import it.polimi.traveldream.entities.UserDTO;

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

	
	public void createAmico(String email) {

		if (!(verificaPresenzaAmicoEm(email))) {

			Amico amico = new Amico();

			amico.setEmail(email);

			manager.persist(amico);

		}
	}

	/**@param idAmico */
	public void removeAmico(String emailAmico) {

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			InvitoBeanRemote invitoLocal = (InvitoBeanRemote) ctx.lookup("InvitoBean/local");

			invitoLocal.removeInvitiCliente(emailAmico);

			//Amico a = findByIdAmico(idAmico);
			
			Amico a = manager.find(Amico.class, emailAmico);
			
			manager.remove(a);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**@param idAmico
	 * @param email*/
	public void updateAmico(String emailAmico) {

		if (verificaPresenzaAmicoEm(emailAmico)) {

			
			Amico amico = manager.find(Amico.class, emailAmico);


			manager.merge(amico);
		}
	}

	
	public AmicoDTO findByEmailAmico(String emailAmico) {

		TypedQuery<Amico> q = manager.createQuery("FROM Amico a WHERE a.email=:new_emailAmico", Amico.class);

		q.setParameter("new_emailAmico", emailAmico);

		Amico amico = q.getSingleResult();

		return amicoToAmicoDTO(amico);
	}

	/**@return ArrayList<idAmico>*/
	public ArrayList<String> findAll() {

		TypedQuery<AmicoDTO> q = manager.createQuery("FROM Amico a", AmicoDTO.class);

		List<AmicoDTO> amici = q.getResultList();

		ArrayList<String> lista = new ArrayList<String>();

		for (int i = 0; i < amici.size(); i++) {

			lista.set(i, amici.get(i).getEmail());
		}

		return lista;
	}


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

	
	
	
	
	
	public AmicoDTO amicoToAmicoDTO(Amico amico) {
	
		AmicoDTO amicoDTO= new AmicoDTO();
		amicoDTO.setEmail(amico.getEmail());
		return amicoDTO;
		
	}
	
	
	public Amico amicoDTOToAmico(AmicoDTO amicoDTO) {
		
		Amico amico= new Amico();
		amico.setEmail(amicoDTO.getEmail());
		return amico;
		
	}



}
