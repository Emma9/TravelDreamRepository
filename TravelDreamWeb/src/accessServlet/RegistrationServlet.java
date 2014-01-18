package accessServlet;

import it.polimi.traveldream.ejb.client.ClienteBeanRemote;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out= response.getWriter();
		try{
			  Context ctx = getInitialContext();
              request.getSession().setAttribute("context", ctx);
             
              ClienteBeanRemote clienteRemoto= (ClienteBeanRemote) ctx
  					.lookup("ClienteBean/remote");
             
              String nome= request.getParameter("nome");
              String cognome= request.getParameter("cognome");
              String codiceFiscale= request.getParameter("codiceFiscale");
              String email= request.getParameter("email");
              String password= request.getParameter("password");

              if(nome.equals("")||cognome.equals("")||email.equals("")||password.length()<8){
                  response.sendRedirect("registrazione.xhtml");
              }else{
            	  long id = clienteRemoto.createCliente(email, password, codiceFiscale, nome, cognome);
            	  
            	  if(id==-1){
            		RequestDispatcher rd= ((ServletRequest) ctx).getRequestDispatcher("registrazione.xhtml");
      				out.println("<p>I dati inseriti non sono corretti </p>");
      				rd.include(request, response);
      				
            	  }else{
            		  request.getSession().setAttribute("idCliente", clienteRemoto.findByIdCliente(id).getIdCliente());
            		  request.getSession().setAttribute("Cliente", clienteRemoto.findByIdCliente(id));
            		  ArrayList<PacchettoPersonalizzato> elencoPacchettiCliente = new ArrayList<PacchettoPersonalizzato>();
      				
                      
                      request.getSession().setAttribute("elencoPacchettiCliente", elencoPacchettiCliente);
                     
                      response.sendRedirect("homepageCliente.xhtml");

            	  }
              }
              
              
		}catch(NamingException e){
			//da implementare!!!!!!!!!!!!!!
		}
		
		// TODO Auto-generated method stub
	}

	private Context getInitialContext() throws NamingException {
		// TODO Auto-generated method stub
		Hashtable<String,String> env = new Hashtable<String,String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        env.put(Context.PROVIDER_URL,"localhost:1099");
        return new InitialContext(env);
	}

}
