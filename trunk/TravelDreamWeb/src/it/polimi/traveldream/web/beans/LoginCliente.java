package it.polimi.traveldream.web.beans;

public interface LoginCliente {

	public String login();

	public String logout();
	
	public String getEmail();

	public void setEmail(String email);

	public String getPassword();
	
	public void setPassword(String password);

}
