package it.polimi.traveldream.web.beans;


import it.polimi.traveldream.ejb.*;


@ManagedBean
@RequestScoped
public class ContentBean {
	@EJB
	private Content content;
	
	public ContentBean(){
		
	}
	
	public String getImpiegatoContent(){
		return content.getImpiegatoContent();
	}
	
	public String getClienteContent(){
		return content.getClienteContent();
	}
}
