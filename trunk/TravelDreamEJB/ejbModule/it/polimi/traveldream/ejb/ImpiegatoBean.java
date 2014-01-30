package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.ImpiegatoBeanLocal;
import it.polimi.traveldream.ejb.client.ImpiegatoBeanRemote;
import it.polimi.traveldream.entities.Impiegato;
import it.polimi.traveldream.entities.ImpiegatoDTO;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

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

			Impiegato impiegato = new Impiegato();
			
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

		//ImpiegatoDTO i = findByIdImpiegato(idImpiegato);
		
		Impiegato i = manager.find(Impiegato.class, idImpiegato);
		
		manager.remove(i);

	}

	/**@param idImpiegato
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome*/
	public void updateImpiegato(Long idImpiegato,String codiceFiscale, String nome, String cognome) {

		if (verificaPresenzaImpiegatoId(idImpiegato)) {

			Impiegato impiegato = manager.find(Impiegato.class, idImpiegato);

			impiegato.setCodiceFiscale(codiceFiscale);
			impiegato.setNome(nome);
			impiegato.setCognome(cognome);

			manager.merge(impiegato);
		}
	}

	/**@param idImpiegato
	 * @return ImpiegatoDTO*/
	public ImpiegatoDTO findByIdImpiegato(Long idImpiegato) {

		TypedQuery<Impiegato> q = manager.createQuery("FROM Impiegato i WHERE i.idImpiegato=:new_idImpiegato", Impiegato.class);

		q.setParameter("new_idImpiegato", idImpiegato);

		ImpiegatoDTO impiegato = impiegatoToDTO(q.getSingleResult());

		return impiegato;
	}
	

	/**@return ArrayList<idImpiegato>*/
	public ArrayList<Long> findAll() {

		TypedQuery<Impiegato> q = manager.createQuery("FROM Impiegato i", Impiegato.class);

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			lista.add(q.getResultList().get(i).getIdImpiegato());
			
		}
		return lista;
	}

	/**@param codiceFiscale
	 * @return true if codiceFiscale is present in the DB, otherwise false*/
	public boolean verificaPresenzaImpiegatoCf(String codiceFiscale) {
		try {
			TypedQuery<Impiegato> q = manager.createQuery("FROM Impiegato i WHERE i.codiceFiscale=:new_codiceFiscale", Impiegato.class);

			q.setParameter("new_codiceFiscale", codiceFiscale);

			if(q.getResultList().isEmpty()){
				
				return false;

			} 
				
			return true;

			
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

			if (q.getResultList().isEmpty()) {
				
				return false;

			}
				
			return true;

			
		} catch (NullPointerException e) {
			
			return false;
		
		}
	}
	
	public ImpiegatoDTO impiegatoToDTO(Impiegato impiegato){
		
		ImpiegatoDTO impiegatoDTO= new ImpiegatoDTO();
		
		impiegatoDTO.setIdImpiegato(impiegato.getIdImpiegato());
		impiegatoDTO.setCodiceImpiegato(impiegato.getCodiceImpiegato());
		impiegatoDTO.setCodiceFiscale(impiegato.getCodiceFiscale());
		impiegatoDTO.setNome(impiegato.getNome());
		impiegatoDTO.setCognome(impiegato.getCognome());
		
		return impiegatoDTO;
		
	}
	
}