package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.ImpiegatoBeanLocal;
import it.polimi.traveldream.ejb.client.ImpiegatoBeanRemote;
import it.polimi.traveldream.entities.Impiegato;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**Session Bean implementation class ImpiegatoBean*/
@Stateless
public class ImpiegatoBean implements ImpiegatoBeanRemote,ImpiegatoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") private EntityManager manager;

	/**Default constructor*/
	public ImpiegatoBean() {
		// TODO Auto-generated constructor stub
	}

	/**@param codiceImpiegato
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @return idImpiegato*/
	public Long createImpiegato(String codiceImpiegato, String codiceFiscale,String nome, String cognome) {

		if (verificaPresenzaImpiegatoCf(codiceFiscale)) {

			Impiegato impiegato = new Impiegato();

			impiegato.setCodiceImpiegato(codiceImpiegato);
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

		Impiegato i = findByIdImpiegato(idImpiegato);
		
		manager.remove(i);

	}

	/**@param idImpiegato
	 * @param codiceImpiegato
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome*/
	public void updateImpiegato(Long idImpiegato, String codiceImpiegato,String codiceFiscale, String nome, String cognome) {

		if (verificaPresenzaImpiegatoId(idImpiegato)) {

			Impiegato impiegato = findByIdImpiegato(idImpiegato);

			impiegato.setCodiceImpiegato(codiceImpiegato);
			impiegato.setCodiceFiscale(codiceFiscale);
			impiegato.setNome(nome);
			impiegato.setCognome(cognome);

			manager.merge(impiegato);
		}
	}

	/**@param idImpiegato
	 * @return Impiegato*/
	public Impiegato findByIdImpiegato(Long idImpiegato) {

		Query q = manager.createQuery("FROM Impiegato i WHERE i.idImpiegato=:new_idImpiegato");

		q.setParameter("new_idImpiegato", idImpiegato);

		Impiegato impiegato = (Impiegato) q.getSingleResult();

		return impiegato;
	}

	/**@return ArrayList<idImpiegato>*/
	public ArrayList<Long> findAll() {

		Query q = manager.createQuery("FROM Impiegato i");

		@SuppressWarnings("unchecked")
		ArrayList<Impiegato> impiegati = (ArrayList<Impiegato>) q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= impiegati.size(); i++) {

			lista.set(i, impiegati.get(i).getIdImpiegato());
			
		}
		return lista;
	}

	/** Metodi private */

	/**@param codiceFiscale
	 * @return true if codiceFiscale is not present in the DB, otherwise false*/
	private boolean verificaPresenzaImpiegatoCf(String codiceFiscale) {
		try {
			Query q = manager.createQuery("FROM Impiegato i WHERE i.codiceFiscale=:new_codiceFiscale");

			q.setParameter("new_codiceFiscale", codiceFiscale);

			@SuppressWarnings("unchecked")
			ArrayList<Impiegato> impiegati = (ArrayList<Impiegato>) q.getResultList();

			if (impiegati.size() == 0) {
				
				return true;

			} else {
				
				return false;

			}
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**@param idImpiegato
	 * @return true if idImpiegato is not present in the DB, otherwise false*/
	private boolean verificaPresenzaImpiegatoId(Long idImpiegato) {
		try {
			Query q = manager.createQuery("FROM Impiegato i WHERE i.idImpiegato=:new_idImpiegato");

			q.setParameter("new_idImpiegato", idImpiegato);

			@SuppressWarnings("unchecked")
			ArrayList<Impiegato> impiegati = (ArrayList<Impiegato>) q.getResultList();

			if (impiegati.size() == 0) {
				
				return true;

			} else {
				
				return false;

			}
		} catch (NullPointerException e) {
			return true;
		}
	}
}
