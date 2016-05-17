package actions.forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import postgreSQLDatabase.forms.Query;
import postgreSQLDatabase.forms.Query.java;
/**
 * Servlet implementation class RetrieveForms
 */
public class RetrieveForms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveForms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer=response.getWriter();
		writer.write("hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer=response.getWriter();
		
		response.setContentType("text/html");
		String formname=request.getParameter("formname");
		String allFields=request.getParameter("allfield");
		String format=request.getParameter("editor1");
	
       try {
		 Query.addForm(formname, allFields, format);
		
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	     }
		
       
	}

}
