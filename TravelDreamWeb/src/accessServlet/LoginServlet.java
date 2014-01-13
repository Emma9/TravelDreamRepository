package accessServlet;

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

import it.polimi.traveldream.ejb.ClienteBeanRemote;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		try {
			Context ctx = getInitialContext();
			request.getSession().setAttribute("context", ctx);

			String mail = request.getParameter("email");
			String password = request.getParameter("password");
			ClienteBeanRemote clienteRemoto= (ClienteBeanRemote) ctx
					.lookup("ClienteBean/remote");


			long id = clienteRemoto.verificaPresenzaClienteLogin(mail, password);

			if (id == -1) {
				RequestDispatcher rd= ((ServletRequest) ctx).getRequestDispatcher("login.xhtml");
				out.println("<p>La password o il nome utente inserito non è corretto </p>");
				rd.include(request, response);
				
			}else{
				request.getSession().setAttribute("idCliente", clienteRemoto.findByEmailCliente(mail).getIdCliente());
				request.getSession().setAttribute("Cliente", clienteRemoto.findByEmailCliente(mail));
				
				ArrayList<PacchettoPersonalizzato> elencoPacchettiCliente = clienteRemoto.elencoPacchettiCliente(id);
				
				request.getSession().setAttribute("ElencoPacchettiCliente", elencoPacchettiCliente);
				response.sendRedirect("homepageCliente.xhtml");

				
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			Context ctx = null;
			try {
				ctx = getInitialContext();
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.getSession().setAttribute("context", ctx);
			RequestDispatcher rd= ((ServletRequest) ctx).getRequestDispatcher("login.xhtml");
			out.println("<p>Si è verificato un errore </p>");
			rd.include(request, response);

		}

	}

	private Context getInitialContext() throws NamingException {
		// TODO Auto-generated method stub
		Hashtable<String,String> env = new Hashtable<String,String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        env.put(Context.PROVIDER_URL,"localhost:1099");
        return new InitialContext(env);
	}

	// pw.println("<h1> Welcome "+mail+"</h1> <br/>");
}
