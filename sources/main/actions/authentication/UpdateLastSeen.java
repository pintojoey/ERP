package actions.authentication;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import settings.database.PostgreSQLConnection;

/**
 * Servlet implementation class UpdateLastSeen
 */
@WebServlet(
		name="Update Last Seen servlet",
		urlPatterns={"/UpdateLastSeen"}
	)
public class UpdateLastSeen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public UpdateLastSeen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

      try {
    	  String erp_id=(String)request.getSession().getAttribute("erpId");
          long  erpid=Long.parseLong(erp_id);
		PreparedStatement proc = PostgreSQLConnection.getConnection().prepareStatement("SELECT public.\"updateLastSeen\"(?);");
		
		proc.setLong(1,erpid);
		proc.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
	}

}
