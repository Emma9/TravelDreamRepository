package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.ImpiegatoBeanLocal;
import it.polimi.traveldream.ejb.client.ImpiegatoBeanRemote;
import it.polimi.traveldream.entities.ImpiegatoDTO;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**Session Bean implementation class ImpiegatoBean*/
@Stateless
public class ImpiegatoBean implements ImpiegatoBeanRemote,ImpiegatoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") private EntityManager manager;

	private SecureRandom random = new SecureRandom();
	
	/**Default constructor*/
	public ImpiegatoBean() {
		// TODO Auto-generated constructor stub
	}

	/**@param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @return idImpiegato*/
	public Long createImpiegato(String codiceFiscale,String nome, String cognome) {

		if (!(verificaPresenzaImpiegatoCf(codiceFiscale))) {

			ImpiegatoDTO impiegato = new ImpiegatoDTO();
			
			BigInteger bi = new BigInteger(130, random);
			String codice = bi.toString(8);
			
			impiegato.setCodiceImpiegato(codice);
			impiegato.setCodiceFiscale(codiceFiscale);
			impiegato.setNome(nome);
			impiegato.setCognome(cognome);

			manager.persist(impiegato);

			return impiegato.getIdImpiegato();

		} else {
			return (long) -1;
		}

	}

	/**@param idImpiegato*/
	public void removeImpiegato(Long idImpiegato) {

		ImpiegatoDTO i = findByIdImpiegato(idImpiegato);
		
		manager.remove(i);

	}

	/**@param idImpiegato
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome*/
	public void updateImpiegato(Long idImpiegato,String codiceFiscale, String nome, String cognome) {

		if (verificaPresenzaImpiegatoId(idImpiegato)) {

			ImpiegatoDTO impiegato = findByIdImpiegato(idImpiegato);

			impiegato.setCodiceFiscale(codiceFiscale);
			impiegato.setNome(nome);
			impiegato.setCognome(cognome);

			manager.merge(impiegato);
		}
	}

	/**@param idImpiegato
	 * @return Impiegato*/
	public ImpiegatoDTO findByIdImpiegato(Long idImpiegato) {

		TypedQuery<ImpiegatoDTO> q = manager.createQuery("FROM Impiegato i WHERE i.idImpiegato=:new_idImpiegato", ImpiegatoDTO.class);

		q.setParameter("new_idImpiegato", idImpiegato);

		ImpiegatoDTO impiegato = q.getSingleResult();

		return impiegato;
	}

	/**@return ArrayList<idImpiegato>*/
	public ArrayList<Long> findAll() {

		TypedQuery<ImpiegatoDTO> q = manager.createQuery("FROM Impiegato i", ImpiegatoDTO.class);

		List<ImpiegatoDTO> impiegati = q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= impiegati.size(); i++) {

			lista.set(i, impiegati.get(i).getIdImpiegato());
			
		}
		return lista;
	}

	/**@param codiceFiscale
	 * @return true if codiceFiscale is present in the DB, otherwise false*/
	public boolean verificaPresenzaImpiegatoCf(String codiceFiscale) {
		try {
			TypedQuery<ImpiegatoDTO> q = manager.createQuery("FROM Impiegato i WHERE i.codiceFiscale=:new_codiceFiscale", ImpiegatoDTO.class);

			q.setParameter("new_codiceFiscale", codiceFiscale);

			List<ImpiegatoDTO> impiegati = q.getResultList();

			if (impiegati.size() == 0) {
				
				return false;

			} else {
				
				return true;

			}
		} catch (NullPointerException e) {
			
			return false;
		
		}
	}

	/**@param idImpiegato
	 * @return true if idImpiegato is present in the DB, otherwise false*/
	public boolean verificaPresenzaImpiegatoId(Long idImpiegato) {
		try {
			TypedQuery<ImpiegatoDTO> q = manager.createQuery("FROM Impiegato i WHERE i.idImpiegato=:new_idImpiegato", ImpiegatoDTO.class);

			q.setParameter("new_idImpiegato", idImpiegato);

			List<ImpiegatoDTO> impiegati = q.getResultList();

			if (impiegati.size() == 0) {
				
				return false;

			} else {
				
				return true;

			}
		} catch (NullPointerException e) {
			
			return false;
		
		}
	}
}