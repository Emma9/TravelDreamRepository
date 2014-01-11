package accessServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;

import it.polimi.traveldream.*;
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
			ClienteBeanRemote clienteRemoto;

			clienteRemoto = (ClienteBeanRemote) ctx
					.lookup("ClienteBean/remote");

			long id = clienteRemoto.verificaPresenzaClienteLogin(mail, password);

			if (id == -1) {
				out.println("<p>La password o il nome utente inserito non è corretto </p>");
			}else{
				request.getSession().setAttribute("idCliente", clienteRemoto.findByEmailCliente(mail).getIdCliente());
				request.getSession().setAttribute("Cliente", clienteRemoto.findByEmailCliente(mail));
				
				ArrayList<PacchettoPersonalizzato> elencoPacchettiCliente = clienteRemoto.elencoPacchettiCliente(id);
				
				request.getSession().setAttribute("ElencoPacchettiCliente", elencoPacchettiCliente);
				response.sendRedirect("login.xhtml");

				
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Context getInitialContext() {
		// TODO Auto-generated method stub
		return null;
	}

	// pw.println("<h1> Welcome "+mail+"</h1> <br/>");
}
