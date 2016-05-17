package actions.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;


import ldap.*;
/**
 * Servlet implementation class SignIn
 */
@WebServlet(
		name="Sign In servlet",
		urlPatterns={"/SignIn"}
	)
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignIn() {
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

		String username=request.getParameter("username");
		String password=request.getParameter("password");

		try {	
			JSONObject credentials=SimpleLdapAuthentication.searchAndAuthenticate(username, password);
			if(credentials!=null){
			
			
			HttpSession session = request.getSession();
			session.setAttribute("name",  credentials.get("name"));
			session.setAttribute("erpId",credentials.get("erpId") );
				JSONObject data = new JSONObject();

				data.put("redirect", credentials.get("type")+"/home.jsp");
				PrintWriter writer = response.getWriter();
				writer.write(data.toString());

			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
