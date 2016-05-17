package actions.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ldap.SimpleLdapAuthentication;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet(
		name="Passwrd Reset Servlet",
		urlPatterns={"/ResetPassword"}
	)
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   

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
		// TODO Auto-generated method stub
		Long erp_id=Long.parseLong( request.getSession().getAttribute("erpId").toString());
		String username=postgreSQLDatabase.authentication.Query.getUserUsername(erp_id);
		System.out.println(username);
		String old_password=request.getParameter("old_password");
		String new_password=request.getParameter("new_password");
		PrintWriter writer = response.getWriter();
		System.out.println(username+ old_password+ new_password);
		boolean reset = SimpleLdapAuthentication.resetPassword(username, old_password, new_password);
		JSONObject reset_status=new JSONObject();
		
		
		if(reset)reset_status.put("reset", "success");
		else reset_status.put("reset", "failure");
		writer.write(reset_status.toString());
	}

}
