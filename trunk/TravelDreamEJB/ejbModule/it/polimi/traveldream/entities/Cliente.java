package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**Cliente di TravelDream*/

@Entity
public class Cliente implements Serializable {

	/**Version number*/
	private static final long serialVersionUID = 103L;

	/**Attributi*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // o IDENTITY???????
	private Long idCliente;
	@Column(unique = true)
	private String email;
	private String password;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private List<PacchettoPersonalizzato> pacchettiCliente = new ArrayList<PacchettoPersonalizzato>(0);
	private List<PacchettoPersonalizzato> giftList = new ArrayList<PacchettoPersonalizzato>(0);

	/**Costruttore*/
	public Cliente() {
		super();
	}
	
	/*
	public Cliente (ClienteDTO clienteDTO){
		this.idCliente=clienteDTO.getIdCliente();
		this.email=clienteDTO.getEmail();
		this.password=clienteDTO.getPassword();
		this.nome=clienteDTO.getNome();
		this.cognome=clienteDTO.getCognome();
		this.codiceFiscale=clienteDTO.getCodiceFiscale();
		
		List<PacchettoPersonalizzato> pacchetti= new ArrayList<PacchettoPersonalizzato>();
		for(int i=0; i<clienteDTO.getPacchettiCliente().size();i++){
			pacchetti.add(clienteDTO.getPacchettiCliente().get(i));
		}
		this.setPacchettiCliente(pacchetti);
		
		List<PacchettoPersonalizzatoDTO> pacchettiGiftList= new ArrayList<PacchettoPersonalizzatoDTO>();
		for(int i=0; i<cliente.getGiftList().size();i++){
			pacchettiGiftList.add(pacchettoPersonalizzatoToDTOInCliente(cliente.getGiftList().get(i)));
		}
		clienteDTO.setGiftList(pacchettiGiftList);
		
	}*/

	/**@return idCliente*/
	public Long getIdCliente() {
		
		return this.idCliente;
		
	}
	
	
	

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}



	/**@return email*/
	public String getEmail() {
		
		return this.email;
		
	}

	/**@param email email to set*/
	public void setEmail(String email) {
		
		this.email = email;
		
	}

	/**@return password*/
	public String getPassword() {
		
		return this.password;
		
	}

	/**@param password password to set*/
	public void setPassword(String password) {
		
		this.password = password;
		
	}

	/**@return codiceFiscale*/
	public String getCodiceFiscale() {
		
		return this.codiceFiscale;
		
	}

	/**@param codiceFiscale codiceFiscale to set*/
	public void setCodiceFiscale(String codiceFiscale) {
		
		this.codiceFiscale = codiceFiscale;
		
	}

	/**@return nome*/
	public String getNome() {
		
		return this.nome;
		
	}

	/**@param nome nome to set*/
	public void setNome(String nome) {
		
		this.nome = nome;
		
	}

	/**@return cognome*/
	public String getCognome() {
		
		return this.cognome;
		
	}

	/**@param cognome cognome to set*/
	public void setCognome(String cognome) {
		
		this.cognome = cognome;
		
	}

	/**
	 * @return the pacchettiCliente
	 */
	public List<PacchettoPersonalizzato> getPacchettiCliente() {
		return pacchettiCliente;
	}

	/**
	 * @param pacchettiCliente the pacchettiCliente to set
	 */
	public void setPacchettiCliente(List<PacchettoPersonalizzato> pacchettiCliente) {
		this.pacchettiCliente = pacchettiCliente;
	}

	/**
	 * @return the giftList
	 */
	public List<PacchettoPersonalizzato> getGiftList() {
		return giftList;
	}

	/**
	 * @param giftList the giftList to set
	 */
	public void setGiftList(List<PacchettoPersonalizzato> giftList) {
		this.giftList = giftList;
	}
	
}