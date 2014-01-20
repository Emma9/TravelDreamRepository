package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.entities.Cliente;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
//import com.sivalabs.pfdemo.mb.ui.RegistrationForm;
@ManagedBean
@RequestScoped
public class RegistrazioneBean {

 private Cliente registrazioneForm = null;
 //private List<String> interests = null;
 private ArrayList<PacchettoPersonalizzato> pacchettiCliente = null;
 private ArrayList<PacchettoPersonalizzato> giftList = null;
 //private List<String> genders = null;
  
 public RegistrazioneBean()
 {
  this.pacchettiCliente = new Cliente().getPacchettiCliente();
  this.giftList = new Cliente().getGiftList();
 
 }
 public String register()
 {
  System.out.println("register.....");
  //store data in DB
  System.out.println(this.registrazioneForm);
  return "homepage";//return "welcome";//go to welcome.xhtml
 }
 public Cliente getRegistrazioneForm()
 {
  if(this.registrazioneForm == null){
   this.registrazioneForm = new Cliente();
  }
  return registrazioneForm;
 }
 public void setRegistrazioneForm(Cliente registrazioneForm)
 {
  this.registrazioneForm = registrazioneForm;
 }
 //servono questi sotto????
 public ArrayList<PacchettoPersonalizzato> getPacchettiCliente()
 {
  return pacchettiCliente;
 }
 public void setPacchettiCliente(ArrayList<PacchettoPersonalizzato> pacchettiCliente)
 {
  this.pacchettiCliente = pacchettiCliente;
 }
  public ArrayList<PacchettoPersonalizzato> getGiftList()
  {
   return giftList;
  }
  public void setGiftList(ArrayList<PacchettoPersonalizzato> giftList)
  {
   this.giftList = giftList;
 }
}
