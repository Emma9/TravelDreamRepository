package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.entities.Cliente;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.sivalabs.pfdemo.mb.ui.RegistrationForm;
@ManagedBean
@RequestScoped
public class RegistrazioneBean implements Registrazione{
	public RegistrazioneBean() {
        // TODO Auto-generated constructor stub
    }
   
    @PersistenceContext(unitName = "traveldream")
    private EntityManager em;
   
   
    
    @Override
    public void inserisciNelDatabase(String nome, String cognome, String codiceFiscale, String email, String password){
       
        
        EmailParser eP = new EmailParser(email);
                Map<String,String> activatingMailParameters = new HashMap<String,String>();
               
        }
               
        PasswordHash ph = new PasswordHash();
       
        try {
                        String hash = ph.createHash(password);
                        System.out.println(hash);
                        User u = new User(firstName, lastName, email, hash, city, false);
                       
                //TODO rimuovere la println
                System.out.println("non esiste tale utente e quindi posso crearlo");
                em.persist(u);
               
                String randomCode = randomString(30);
                String activationLink = "http://localhost:8080/SWIMv2_DWP/confirm?activationcode=" + randomCode + "&user=" + email;
               
                activatingMailParameters = generateActivationMailParameters(activatingMailParameters, email, firstName, lastName, activationLink, randomCode);
               
                
                System.out.println("ho inviato correttamente la mail");
               
                u.setRegistrationCode(randomCode);
                u.setConfirmed(false);
                //TODO va fatto dopo ogni modifica o si può togliere?
                em.persist(u);
               
                       
                } catch (NoSuchAlgorithmException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                } catch (InvalidKeySpecException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                } catch(Exception e){
                //TODO rimuovere la println e, se vogliamo essere precisi, sostituire a "Exception" il nome della sua sottoclasse che corrisponde all'eccezione lanciata
                e.printStackTrace();
                        System.out.println("l'email esiste già: l'utente non viene creato");
        }
    }
   
    @Override
    public boolean isEmailAvailable(String email){
       
        try{
                Query q = em.createQuery("SELECT u.email FROM GenericUser u WHERE u.email = :email");
                q.setParameter("email", email);
               
                String e = (String)q.getSingleResult();
               
                return false;
        }
        catch(Exception e){
                return true;
        }
       
       
       
    }



}
